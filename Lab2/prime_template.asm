	.data
a:
	999
	.text
main:
	load %x0, $a, %x3
	divi %x3, 2, %x4
	add %x0, %x0, %x10
	addi %x0, 2, %x5
loop:
	div %x3, %x5, %x6
	beq %x31, 0, np
	addi %x5, 1, %5
	bgt %5, %x4, endl
	jmp loop
np:
	subi %x10, 1, %x10
	end
endl:
	addi %x10, 1, %x10
	end
