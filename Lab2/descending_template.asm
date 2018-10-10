	.data
a:
	70
	80
	40
	20
	50
	30
	60
	10
n:
	8
	.text
main:
	load %x0, $n, %x3
mainloop:
	load %x0, $n, %x4
	addi %x0, 1, %x5
	addi %x0, 0, %x6
	subi %x3, 1, %x3
	beq %x3, %x0, endl
nestedloop:
	subi %x4, 1, %x4
	beq %x4, %x0, mainloop
	load %x6, $a, %x7
	load %x5, $a, %x8
	blt %x7, %x8, swap
	addi %x6, 1, %x6
	addi %x5, 1, %x5
	jmp nestedloop
swap:
	store %x8, 0, %x6
	store %x7, 0, %x5
	addi %x6, 1, %x6
	addi %x5, 1, %x5
	jmp nestedloop
endl:
	end
