;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Your solution goes in this file ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; length function
(defun all-length (list)
    

    (cond ((null list) 0)
          ((atom list) 1)
	  (t (+ (all-length(car list)) (all-length(cdr list))))
    )
)

; range function
(defun range (l)
    ;list((apply 'min l) (apply 'max l))
)

; before function
(defun before(list)

)

; split-if function
(defun split-if(f l)

)

; group function
(defun group(l n)

)

; mostn function
(defun mostn(f l)

)
