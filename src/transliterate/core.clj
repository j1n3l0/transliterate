(ns transliterate.core
  (import [org.jtr.transliterate CharacterParser CharacterReplacer]))

(def flags {:c CharacterParser/COMPLEMENT_MASK
            :d CharacterParser/DELETE_UNREPLACEABLES_MASK
            :s CharacterParser/SQUASH_DUPLICATES_MASK})

(defn tr
  [from to string & options]
  (. (doto (new CharacterReplacer from to)
       (. setFlags (apply + (map #(flags %) options))))
     doReplacement string))
