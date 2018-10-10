	.data
n:
	12
	.text
main:
	load %x0, $n, %x3
	addi %x0, 65535, %x5
	addi %x0, 0, %x6
	addi %x0, 1, %x7
	store %x6, 0, %x5
	subi %x5, 1, %x5
	store %x7, 0, %x5
	subi %x3, 2, %x3
loop:
	add %x6, %x7, %x8
	subi %x5, 1, %x5
	store %x8, 0, %x5
	subi %x3, 1, %x3
	beq %x3, %x0, endl
	addi %x7, 0, %x6
	addi %x8, 0, %x7
	jmp loop
endl:
	end
