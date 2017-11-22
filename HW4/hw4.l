;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Your solution goes in this file ;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defun all-length(alist count)
	(loop for sublist in alist
		(if (listp sublist)
			(progn
				(all-length sublist count)
			)
			;else
			(progn
				(= count (+ count 1))
			)
		)
	)
)

(all-length '(1 2 3 4) 0)