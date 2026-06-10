plugins {
    `java-library`
    `maven-publish`
}

group = "cloud.aster-lang"
version = "1.0.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "aster-lang-de"
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/aster-cloud/${rootProject.name}")
            credentials {
                username = System.getenv("GITHUB_ACTOR") ?: ""
                password = System.getenv("GITHUB_TOKEN") ?: ""
            }
        }
    }
}

dependencies {
    implementation("cloud.aster-lang:aster-lang-core:1.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter:6.0.0")
    testImplementation("org.assertj:assertj-core:3.27.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // Canonicalizer 内部依赖 en-US 词法表作为翻译目标
    testRuntimeOnly("cloud.aster-lang:aster-lang-en:0.0.1")
}

tasks.test {
    useJUnitPlatform()
}

/**
 * verifyLexiconKeywordParity (P2-R21 audit):
 *   Check that de-DE.json's `keywords` map contains the same key set as
 *   the canonical en-US.json keys. Per-language values differ (translations);
 *   what must match is the set of SemanticTokenKind names.
 *
 * Why: aster-lang-core's FallbackLexicon assumes en-US is the keyword backbone.
 * If de-DE adds or drops a key while en-US doesn't, runtime translation drifts
 * silently. CI catches the drift at build time.
 */
tasks.register("verifyLexiconKeywordParity") {
    group = "verification"
    description = "Ensure de-DE.json keyword set matches en-US backbone"

    val ours = file("src/main/resources/lexicons/de-DE.json")
    // backbone 可选（非 monorepo CI 下 ../aster-lang-core 不存在）。用 inputs.files(...).optional()
    // 而非 inputs.file()——后者会在 task 执行前强制校验存在，使下方 exists() skip 逻辑失效。
    val backbone = file("../aster-lang-core/src/main/resources/builtin/en-US.json")

    inputs.file(ours)
    inputs.files(backbone).optional()

    doLast {
        if (!backbone.exists()) {
            logger.warn(
                "verifyLexiconKeywordParity: en-US backbone not found at ${backbone.absolutePath}. " +
                    "Sibling aster-lang-core absent — likely non-monorepo CI. Skipping."
            )
            return@doLast
        }
        // groovy.json is on the gradle classpath; pull keyword keys from both.
        val parser = groovy.json.JsonSlurper()
        @Suppress("UNCHECKED_CAST")
        val oursKeywords = ((parser.parse(ours) as Map<String, Any>)["keywords"] as Map<String, Any>).keys
        @Suppress("UNCHECKED_CAST")
        val backboneKeywords = ((parser.parse(backbone) as Map<String, Any>)["keywords"] as Map<String, Any>).keys

        val onlyInOurs = oursKeywords - backboneKeywords
        val onlyInBackbone = backboneKeywords - oursKeywords
        if (onlyInOurs.isNotEmpty() || onlyInBackbone.isNotEmpty()) {
            throw GradleException(
                "de-DE.json keyword drift:\n" +
                    "  only in de-DE: $onlyInOurs\n" +
                    "  only in en-US: $onlyInBackbone\n" +
                    "Sync the keyword set across all lexicon repos before merging."
            )
        }
        logger.lifecycle(
            "verifyLexiconKeywordParity: de-DE.json keyword set matches en-US backbone (${oursKeywords.size} keys) ✓"
        )
    }
}

tasks.named("check") {
    dependsOn("verifyLexiconKeywordParity")
}
