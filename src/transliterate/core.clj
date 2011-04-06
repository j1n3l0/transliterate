(ns transliterate.core
  (import [org.jtr.transliterate CharacterReplacer Perl5Parser]))

(defn tr
  "Uses jtr 1.1 from http://jtr.sourceforge.net/"
  [pattern string]
  (let [replacer (. Perl5Parser makeReplacer pattern)] (. replacer doReplacement string)))
