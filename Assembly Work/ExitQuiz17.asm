.data
Z : .word 0
A : .word 0
B : .word 0
	
.text
# Z = (A +2) + (B - 4)
	lw $s0,a 		#loading A
	lw $s1,b 		#loading b


	add $s0,2 	 	#getting a + 2
	add $	 		#getting (x - y)/4
	sub $s1,4		#getting B - 4
	sw $s2,z 		# z = (x - y)%4
	
