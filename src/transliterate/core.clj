(ns transliterate.core
  (:import [org.jtr.transliterate CharacterParser CharacterReplacer]))

(defn tr
  "Performs Perl 5-style transliterations on a given string"
  [search-list replacement-list string & options]
  (let [flags              {:c CharacterParser/COMPLEMENT_MASK
                            :d CharacterParser/DELETE_UNREPLACEABLES_MASK
                            :s CharacterParser/SQUASH_DUPLICATES_MASK}
        opt-flags          (reduce + (map #(get flags % 0) options))
        character-replacer (doto (new CharacterReplacer search-list replacement-list) (.setFlags opt-flags))]
    (.doReplacement character-replacer string)))
