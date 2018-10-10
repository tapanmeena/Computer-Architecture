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
	.text
main:
	load %x0, $n, %x3
loop:
	load %x0, $n, %x4
	addi %x0, 0, %x5
	addi %x0, 1, %x6
	subi %x3, 1, %x3
	beq %x3, %x0, success
innerloop:
	subi %x4, 1, %x4
	beq %x4, %x0, loop
	load %x5, $a, %x7
	load %x6, $a, %x8
	blt %x7, %x8, swap
	store %x7, 0, %x5
	store %x8, 0, %x6
	addi %x5, 1, %x5
	addi %x6, 1, %x6
	jmp innerloop
swap:
	add %x7, %x0, %x9
	add %x8, %x0, %x7
	add %x9, %x0, %x8
	store %x7, 0, %x5
	store %x8, 0, %x6
	addi %x5, 1, %x5
	addi %x6, 1, %x6
	jmp innerloop
success:
	end
