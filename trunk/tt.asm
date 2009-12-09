.ORIG x3000
LD R2, TERM	 ; Load negative ASCII		
LD R3, ASCII	 ; Load ASCII difference
AGAIN	TRAP x23 ; input character
ADD R1, R2, R0	 ; Test for terminate
BRz	EXIT		 ; Exit if done
ADD	R0, R0, R3	 ; Change to lowercase		
TRAP	 x21		 ; Output to monitor...		
BRnzp  AGAIN	 ; ... again and again..
TERM	.FILL	 xFFC9	 
ASCII		.FILL	 x0020	 ; lowercase bit
STRING .STRINGZ "Hello World!"
RET
RTI
EXIT		TRAP	 x25		 ; halt
.END
