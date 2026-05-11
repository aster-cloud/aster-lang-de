# Beitrag zu Aster Lang Lexica

> Helfen Sie, Policy-as-Code in Ihrer Muttersprache verfügbar zu machen.

> **Maßgebliche englische Version**: [aster-lang-en/CONTRIBUTING.md](https://github.com/aster-cloud/aster-lang-en/blob/main/CONTRIBUTING.md)
> Diese deutsche Übersetzung dient der Bequemlichkeit; bei Unklarheit gilt die englische Version.

---

## 1. Wählen Sie Ihren Beitragspfad

| Pfad | Was Sie tun | Aster-Beteiligung | Geeignet für |
|---|---|---|---|
| **Offizielles Lexikon** | Aster-Team direkte Wartung | 100 % | en / zh / de (Kernmärkte) |
| **Offiziell befürwortetes Lexikon** | Template forken → übersetzen → PR an aster-cloud-Organisation | Review + Sicherheitsaudit + Maven-Veröffentlichung | Verbreitete Sprachen (ja / fr / es / ...) |
| **Community-Lexikon** | Template forken → übersetzen → in Ihrer eigenen GitHub-Org pflegen | Nur Dokumentationslisting, keine Befürwortung | Long-Tail-Sprachen / Branchen-Dialekte |

---

## 2. Beitragsablauf (offiziell befürwortet)

1. Prüfen Sie das [Wanted Languages Board](https://aster-lang.dev/community/wanted-languages) — Ihre Sprache könnte bereits einen offenen PR oder bezahlten Autor haben.
2. Forken Sie [`aster-lang-template`](https://github.com/aster-cloud/aster-lang-template) → folgen Sie dem **15-Minuten-Tutorial** in dessen README.
3. Öffnen Sie einen PR an `aster-cloud/aster-lang-<lang>-<region>` (das Repository wird vom Aster-Team nach Erstanfrage erstellt).
4. CI validiert das Lexikon-JSON + Aster-Reviewer genehmigt → Merge.
5. Aster veröffentlicht auf Maven Central / npm.
6. Sie werden dem Mitwirkenden-Verzeichnis hinzugefügt.

---

## 3. Übersetzungsregeln

### 3.1 Schlüsselwort-Übersetzungsprinzipien

- ✅ **Bevorzugen Sie Fachterminologie** gegenüber umgangssprachlichen Begriffen
  - z. B. `Module` als `Modul` übersetzen, nicht als „Gruppe"
- ✅ Schlüsselwörter müssen **innerhalb eines Lexikons eindeutig** sein (verschiedene Keys dürfen nicht auf denselben String mappen, außer in `canonicalization.allowedDuplicates` gelistet)
- ❌ Schlüsselwörter dürfen **keine Aster-reservierten Zeichen** enthalten: `[](),.;:=`
- ❌ Schlüsselwörter dürfen nicht mit Ziffern beginnen
- ✅ Mehrwort-Schlüsselwörter mit Leerzeichen trennen (nicht Unterstrich)

### 3.2 Satzzeichen

Jedes Lexikon muss drei Trennzeichen explizit deklarieren:

- `listSeparator` (Standard `,`)
- `enumSeparator` (Standard `,`)
- `statementEnd` (Standard `.`)

Deutsche / französische Lokalisierungen verwenden möglicherweise `,` für Dezimalstellen — stellen Sie sicher, dass `listSeparator` nicht kollidiert.

### 3.3 Vokabulare (optional, Branchen­terminologie)

Wenn Ihre Sprache branchenspezifische Begriffspakete besitzt (z. B. deutsche Versicherung `insurance-auto-de-DE.json`), legen Sie diese in `src/main/resources/vocabularies/` ab.

---

## 4. Review-SLA

| Stufe | Aster-Team-Zusage |
|---|---|
| Erste Bestätigung | **24 Stunden** (Label + Reviewer zugewiesen) |
| Erste vollständige Review | **7 Tage** (substanzielles Feedback zu Übersetzungsqualität + technischer Korrektheit) |
| Merge oder Endentscheidung | **30 Tage** (kein PR wird vergessen) |

---

## 5. Reviewer-Anforderungen

- Der Reviewer **muss Muttersprachler der Zielsprache sein**, oder professionelle Übersetzungsqualifikation nachweisen.
- Mindestens 1 Aster-Team-Mitglied agiert als **Co-Reviewer** für technische Korrektheit (SPI-Konformität, Build-Integrität, Sicherheit).
- **Beide müssen genehmigen**, bevor gemergt wird.

---

## 6. DCO + CLA

- Alle Commits müssen Sign-off enthalten: `git commit -s` ([Developer Certificate of Origin](https://developercertificate.org/))
- Bedeutende Beiträge (≥ 1 vollständiger Lexikon-PR) erfordern Unterzeichnung des Aster CLA (einmalig, elektronisch).

---

## 7. Anreize — Aster Language Steward-Programm

Mergen Sie **≥ 2 Lexika** ODER pflegen Sie 1 Lexikon ≥ **12 Monate**:

- 🏷️ „Aster Language Steward"-Abzeichen in der Mitwirkenden-Liste
- 💰 **¥3.000 / Jahr Plattform-Guthaben** (Steward-exklusiv)
- 📝 Öffentliche Listung im [Mitwirkenden-Verzeichnis](https://aster-lang.dev/community/contributors)
- 🎙️ Priorisierte Teilnahme an neuen SPI ABI-Designdiskussionen

---

## 8. Wartungs­verpflichtung

**Sie verpflichten sich:**
- ABI-Upgrades zu verfolgen (v1 → v2 mit 6-monatigem Migrationsfenster)
- Community-gemeldete Übersetzungs­fehler zu beheben
- Vierteljährlich auf Lexikon-bezogene Issues zu antworten

**Das Aster-Team stellt bereit:**
- Mindestens 1 Co-Maintainer als Rückfallebene
- 6 Monate Vorlauf für ABI-Breaking-Changes
- Übersetzungsqualitäts-Review (kein Sprach-Nitpicking)

---

## 9. Verhaltenskodex

Seien Sie respektvoll. Meinungsverschiedenheiten sind in Ordnung — persönliche Angriffe nicht. Als Basis siehe [Contributor Covenant v2.1](https://www.contributor-covenant.org/version/2/1/code_of_conduct/).

---

## License

Mit Ihrem Beitrag stimmen Sie zu, dass Ihre Beiträge unter [Apache License 2.0](LICENSE) lizenziert werden.
