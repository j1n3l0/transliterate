(ns transliterate.core
  (import [org.jtr.transliterate Perl5Parser]))

(defn tr
  "Uses jtr 1.1 from http://jtr.sourceforge.net/"
  ;; ([pattern replacement string])
  ([pattern string]
     (. (Perl5Parser/makeReplacer pattern) doReplacement string)))
