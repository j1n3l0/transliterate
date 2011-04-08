(ns org.clojars.j1n3l0.transliterate.test.core
  (:use [org.clojars.j1n3l0.transliterate.core] :reload)
  (:use [clojure.test]))

(deftest test-tr
  (let [input "This is a test, 123."]
    (testing "Simple transliteration"
      (is (= "tHIS IS A TEST, 876." (tr "a-zA-Z0-9" "A-Za-z9876543210" input))))
    (testing "Simple short replacement"
      (is (= "____ __ _ ____, ___." (tr "a-zA-Z0-9" "_" input))))
    (testing "Null replacement"
      (is (= input (tr "a-zA-Z0-9" "" input)))
      (is (= input (tr "a-zA-Z0-9" "a-zA-Z0-9" input))))
    (testing "Simple duplicate search list"
      (is (= "This is x test, 123." (tr "aaa" "xyz" input))))
    (testing "Simple compliment"
      (is (= "This_is_a_test__123_" (tr "a-zA-Z0-9" "_"  input :c))))
    (testing "Simple squish"
      (is (= "_ _ _ _, _." (tr "a-zA-Z0-9" "_" input :s))))
    (testing "Simple unreplaceable"
      (is (= "hisisatest,." (tr "a-zA-Z0-9 " "a-z" input :d)))
      (is (= ",." (tr "a-zA-Z0-9 " "" input :d))))
    (testing "Compliment and squish"
      (is (= "This_is_a_test_123_" (tr "a-zA-Z0-9" "_"  input :c :s))))
    (testing "Complement and unreplaceable"
      (is (= "This is a test 123" (tr "a-zA-Z0-9 " "" input :c :d)))
      (is (= "This is a test: 123:" (tr "a-zA-Z0-9 " ":" input :c :d))))
    (testing "All modifiers combined"
      (is (= "This is a test: 123:" (tr "a-zA-Z0-9 " ":" input :c :d :s))))
    (testing "Invalid modifiers"
      (is (= "tHIS IS A TEST, 876." (tr "a-zA-Z0-9" "A-Za-z9876543210" input :f))))
    (testing "Octal character replacement"
      (is (= "This_is_a_test__123_" (tr "\\040-\\057" "\\137" input))))
    (testing "Hexadecimal character replacement"
      (is (= "This_is_a_test_123_" (tr "\\x20-\\x2f" "\\x5f" input :s)))
      (is (= "This\u263ais\u263aa\u263atest\u263a123\u263a" (tr "\\x{0020}-\\x{2f}" "\\x{263a}" input :s))))
    (testing "Other special characters"
      (is (= "/This/is/a/test,/123." (tr "\\\\" "\\/"  "\\This\\is\\a\\test,\\123.")))
      (is (= input (tr "\n\t " " " "This\tis \ta\ttest,\n123." :s)))
      (is (= input (tr "\\n\\t " " " "This\tis \ta\ttest,\n123." :s)))
      (is (= "This\007is\007a\007test\007123\007" (tr "\\x20-\\x2f" "\\cg" input :s)))
      (is (= "This\007is\007a\007test\007123\007" (tr "\\x20-\\x2f" "\\cG" input :s)))
      (is (= "This is a test, -123." (tr "a-z_-" "" "This is a test, -123." :s))))))
