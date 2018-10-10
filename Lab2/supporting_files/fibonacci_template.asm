	.data
n:	
	10
	.text
main:
	load %x0, $n, %x7
	addi %x0, 65535, %x3
	addi %x0, 65534, %x4
	addi %x0, 0, %x5
	store %x5, 0, %x3
	beq %x7, 1, success
	addi %x0, 1, %x6
	store %x6, 0, %x4
	beq %x7, 2, success
	subi %x7, 2, %x7
loop:
	beq %x7, 0, success
	subi %x7, 1, %x7
	subi %x4, 1, %x4
	add %x5, %x6, %x8
	store %x8, 0, %x4
	add %x6, %x0, %x5
	add %x8, %x0, %x6
	jmp loop
success:
	end
