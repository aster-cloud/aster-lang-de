package aster.lang.de;

import aster.core.lexicon.DeDeLexicon;
import aster.core.lexicon.Lexicon;
import aster.core.lexicon.LexiconPlugin;

/**
 * Deutsches Sprachpaket-Plugin (de-DE).
 * <p>
 * Registriert das deutsche Lexikon ueber den SPI-Mechanismus
 * im {@link aster.core.lexicon.LexiconRegistry}.
 * Deutsch verwendet benutzerdefinierte Regeln (customRules) fuer die Umlaut-Normalisierung
 * und benoetigt keine spezialisierten Transformatoren.
 */
public final class DeDeLexiconPlugin implements LexiconPlugin {

    @Override
    public Lexicon createLexicon() {
        return DeDeLexicon.INSTANCE;
    }
}
