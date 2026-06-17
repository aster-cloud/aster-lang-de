# aster-lang-de -- Aster CNL 德语语言包

> ## ⚠️ DEPRECATED — 此仓库已废弃 (ADR-0011)
>
> **本独立仓库不再发布。** 英文/中文/德文语言包（`cloud.aster-lang:aster-lang-{en,zh,de}`）
> 现已统一由合并仓库 **[aster-lang-locales](https://github.com/aster-cloud/aster-lang-locales)**
> 发布（模块 `locales/de`）。工件坐标保持不变，消费者无需改动依赖。
>
> - 新的规范来源 / canonical home: **aster-lang-locales**（ADR-0011 全量切换）
> - 本仓库的 `release.yml` 发布已被停用，以避免重复发布相同 GAV。
> - 源码与 git 历史保留作存档；后续 lexicon 变更请在 aster-lang-locales 进行。
> - 关联 issue: #8（已被本次切换取代 / superseded by the cutover）。

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)

## 概述

提供德语（de-DE）词法表、领域词汇和规范化规则。
通过 Java SPI 机制自动注册到 `aster-lang-core` 的词法表和词汇表注册中心。

## 包含内容

| 类别 | 数量 | 说明 |
|------|------|------|
| 词法表 (lexicon) | 74 关键词 | `lexicons/de-DE.json` |
| 领域词汇 | 2 | 汽车保险 (`insurance-auto`)、贷款金融 (`finance-loan`) |
| 叠加层 (overlay) | 2 | 类型推断规则 (`type-inference`)、输入生成规则 (`input-generation`) |

## SPI 插件

`DeDeLexiconPlugin` 同时实现 `LexiconPlugin` 和 `VocabularyPlugin` 两个接口：

- `createLexicon()` -- 从 JSON 加载 de-DE 词法表
- `createVocabulary()` / `getVocabularies()` -- 加载 2 套领域词汇
- `getOverlayResources()` -- 提供 2 个叠加层资源路径

## 规范化规则 (Canonicalization)

德语使用词法表 JSON 中的 `customRules` 进行规范化，不需要专用的 `SyntaxTransformer`：

| 规则类别 | 示例 |
|----------|------|
| Umlaut 规范化 | `oe` -> `ö`、`ue` -> `ü`、`ae` -> `ä`、`groesser` -> `groesser` |
| 冠词移除 | der / die / das / ein / eine / einen 等 |

Umlaut 规范化确保用户在无法输入特殊字符时仍能正确书写德语关键词。

## 构建与测试

```bash
./gradlew build
./gradlew test
```

依赖：`aster-lang-core:0.0.1`，Java 25，JUnit 6，AssertJ 3.27。
测试时需要 `aster-lang-en` 作为运行时依赖（Canonicalizer 翻译目标）。

## 发布

通过 GitHub Packages 发布：

```
cloud.aster-lang:aster-lang-de:0.0.1
```

## 许可证

Apache License 2.0
