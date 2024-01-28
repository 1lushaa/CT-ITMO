// модуль, который реализует расширенение
// 16-битной знаковой константы до 32-битной
module sign_extend(in, out);
  input [15:0] in;
  output [31:0] out;
  assign out = {{16{in[15]}}, in};
endmodule

// модуль, который реализует побитовый сдвиг числа
// влево на 2 бита
module shl_2(in, out);
  input [31:0] in;
  output [31:0] out;

  assign out = {in[29:0], 2'b00};
endmodule

// 32 битный сумматор
module adder(a, b, out);
  input [31:0] a, b;
  output [31:0] out;

  assign out = a + b;
endmodule

// 32-битный мультиплексор
module mux2_32(d0, d1, a, out);
  input [31:0] d0, d1;
  input a;
  output [31:0] out;
  assign out = a ? d1 : d0;
endmodule

// 5 - битный мультиплексор
module mux2_5(d0, d1, a, out);
  input [4:0] d0, d1;
  input a;
  output [4:0] out;
  assign out = a ? d1 : d0;
endmodule

module alu(a, b, code, res, zero);
  input [31:0] a, b;
  input [2:0] code;
  output reg [31:0] res;
  output reg zero;

  always @* begin
    case(code)
      3'b000: begin
        res = a & b;
      end
      3'b001: begin
        res = a | b;
      end
      3'b010: begin
        res = a + b;
      end
      3'b011: begin
        res = 0;
      end
      3'b100: begin
        res = a & (~b);
      end
      3'b101: begin 
        res = a | (~b);
      end
      3'b110: begin
        res = 1 + a + ~b;
      end
      3'b111: begin
        if(a < b) begin
          res = 1;
        end else begin
          res = 0;
        end
      end
    endcase
    if (res == 0) begin
        zero = 1;
      end else begin
        zero = 0;
      end
  end
endmodule
