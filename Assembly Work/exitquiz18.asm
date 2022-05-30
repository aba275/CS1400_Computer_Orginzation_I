
# if ( i == j && i == k )
# i++ ; // if-body
# else
# j-- ; // else-body
# j = i + k ;

.text	
if:
	bne  $t1, $t2, else	# if ( i == j )
     	bne  $t1, $t3, next	# if ( i == k )
      	addi $t1, $t1, 1	# if i++
      	j next			# jump over else
else: 
	addi $t2, $t2, -1	# j-- 
next: 
	add $t2, $t1, $t3	# j = i +k



