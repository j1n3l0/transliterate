(ns transliterate.test.core
  (:use [transliterate.core] :reload)
  (:use [clojure.test]))

(deftest test-tr
  (testing "Simple transliteration"
    (is (= "_ _ _ _, _." (tr "a-zA-Z0-9" "_" "This is a test, 123." :s)) "example off jtr works")
    (is (= "Aryb" (tr "A-Za-z" "N-ZA-Mn-za-m" "Nelo")) "rot 13 works ...")
    (is (= "Nelo" (tr "A-Za-z" "N-ZA-Mn-za-m" "Aryb")) "both ways")))
