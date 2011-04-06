(ns transliterate.test.core
  (:use [transliterate.core] :reload)
  (:use [clojure.test]))

(deftest test-tr
  (testing "Simple transliteration"
    (is (= "_ _ _ _, _." (tr "tr/a-zA-Z0-9/_/s" "This is a test, 123.")) "example off jtr works")
    (is (= "Aryb" (tr "tr/A-Za-z/N-ZA-Mn-za-m/" "Nelo")) "rot 13 works ...")
    (is (= "Nelo" (tr "tr/A-Za-z/N-ZA-Mn-za-m/" "Aryb")) "both ways")))
