/*******************************************/
/**    Your solution goes in this file    **/ 
/*******************************************/

fc_course(X) :- 
	course(X,Y,3);
	course(X,Y,4).

prereq_110(P) :-
	course(P,C,3),
	member(ecs110,C);
	course(P,D,4),
	member(ecs110,D).

ecs140a_students(N) :-
	student(N,C),
	member(ecs140a,C).

instructor_names(I) :-
	student(john,T),
	instructor(I,E),
	check(E,T).

students(S) :-
	instructor(jim,E),
	student(S,T),
	check(T,E).

check(L1,L2) :-
	member(X,L1),
	member(X,L2),!.

allprereq(C,PreList) :-
	course().
