;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
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
(defun range (r-list)
    (list (apply 'min r-list) (apply 'max r-list))
)

(print (range '( 1 2 3 4)))

; before function
(defun before(index1 index2 list)
	(if (null list)
		;else for not a list
		(list)
	;only do this if its a list
	(if (and (numberp (position index1 list))  (numberp (position index2 list)) )
		;next check if the two indexes are the same
		(if (= (position index2 list) (position index1 list) )
			(nth index1 list)
		;else
		(if (< (position index2 list) (position index1 list) )
			(let ((first index1)
				(second index2))
					;do this with the let varibles
					(if (numberp (position second (member first list))) (
						;list (member first list)
						butlast (member first list) (+ (position second (member first list)) )
						)
					;else return rest of array from first
					(member first list)
					)
			)
			;else
			(let ((first index1)
				(second index2))
					;do this with the let varibles
					(if (numberp (position second list)) (
						;list (member first list)
						butlast (member first list) (+ (position second (member first list)) -1)
						)
					;else return rest of array from first
					(member first list)
					)
			)
		)
		)

	;else for both arguments in the list(its another if)
	(if (null (position index2 list))
		;NOT CORRECT FIX PLS
		;(list (nth 
			(member index1 list)
			; list))
		;(print "yep")
	)

	);end not list if


	)
)

; left-list function
(defun left-list(f l)
    (cond ((null l) nil)
	  ((not (funcall f (car l))) (append (list (car l)) (left-list f (cdr l))))
	  (t (left-list f (cdr l)))
    )
)

; right-list function
(defun right-list(f l)
    (cond ((null l) nil)
	  ((funcall f (car l)) (append (list (car l)) (right-list f (cdr l))))
	  (t (right-list f (cdr l)))
    )
)

; split-if function
(defun split-if(f l)
    (list (left-list f l) (right-list f l))
)

(print (split-if #'(lambda (x) (> x 4)) '(1 2 3 4 5 6 7 8 9 10)))

; group function
(defun group(l n)
    (sublist l n ())
)

; group helper function
(defun sublist(l n subl)
    
    (cond ((and (null l) (= n 1)) nil)
	  ((null l) (list subl)) ;for remaining elements less than n
	  ((< (length subl) n) (sublist (cdr l) n (append subl (list (car l)))))
	  ((= (length subl) n) (append (list subl) (sublist l n nil)))
    )
)

(print (group '(a b c d e f g) 2))


; mostn function
(defun mostn(function alist &optional largestlist)
	;if the first thing in alist is the biggest we found so far
	(if (null (car largestlist) )

		(mostn function (cdr alist) (append (list (car alist)) (list (apply function (list(car alist)) )) ) )

		(if (null (cdr alist) )
			;call mostn with the rest of the list and construct the new largestlist
			(car (list largestlist))

			;else
			(if (= (apply function (list (car alist)) ) (apply function (list (car largestlist) ) ))
				(append (list (car alist)) largestlist) 
				;else
				(if (> (apply function (list(car alist)) ) (apply function (list (car largestlist)) ) )
					(mostn function (cdr alist) (append (list (car alist)) (list (apply function (list(car alist)) )) ) )

					;else
					(mostn function (list(cdr alist)) largestlist )
				)

			)
		)

	)
)

(defun match(l1 l2)
    (cond 
	  ((equal '? (car l1)) (match (cdr l1) (cdr l2)))
	  ((not (equal l1 l2)) nil)
	  ((equal l1 l1) T)
    )

)

(print (match '(think about that) '(think that about)))
(print (match '(think about that) '(think about that)))
(print (match '(color apple ?) '(color apple red)))
