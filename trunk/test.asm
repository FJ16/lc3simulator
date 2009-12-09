	.ORIG x3000
	LEA R6, Stack
	LEA R1, TimeSub
	STI R1, Int81
	AND R1, R1, #0
	ADD R1, R1, #1
	STI R1, Enable
Loop	LD R2, Counter
	BRP Loop
	HALT
Int81	.FILL x0181
Enable	.FILL x1000
Counter	.FILL #30
ASCII	.FILL x0030
	.BLKW 100 #0
Stack	.FILL #0
TimeSub	ST R0, SaveR0	; Save R0 so that changes aren't seen outside the subroutine
	ST R4, SaveR4	; Save R4 so that changes aren't seen outside the subroutine
	.FILL xD800 ; RESERVED R4
	LD R0, ASCII
	ADD R0, R4, R0
	TRAP x21
	LD R0, Counter
	ADD R0, R0, #-1
	ST R0, Counter
	LD R0, SaveR0
	LD R4, SaveR4
	RTI
SaveR0 .FILL #0
SaveR4 .FILL #0
	.END