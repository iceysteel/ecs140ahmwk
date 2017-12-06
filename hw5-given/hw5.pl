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
