.text
clac
add var1
add var2
stor ans  
.label halt
    clac
    beqz halt
# Answer is in data memory at address 0.
.data
.label ans 
    .number 0
.label var1
     .number 5
.label var2 
     .number 2
