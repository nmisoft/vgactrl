## Clock signal
set_property PACKAGE_PIN	E3		[get_ports {clock}];
set_property IOSTANDARD		LVCMOS33	[get_ports {clock}];
create_clock -period 10.0			[get_ports {clock}];

## Pmod Header JA
set_property PACKAGE_PIN	G13		[get_ports { io_vga_red[0] }];
set_property PACKAGE_PIN	B11		[get_ports { io_vga_red[1] }];
set_property PACKAGE_PIN	A11		[get_ports { io_vga_red[2] }];
set_property PACKAGE_PIN	D12		[get_ports { io_vga_red[3] }];
set_property PACKAGE_PIN	D13		[get_ports { io_vga_blue[0] }];
set_property PACKAGE_PIN	B18		[get_ports { io_vga_blue[1] }];
set_property PACKAGE_PIN	A18		[get_ports { io_vga_blue[2] }];
set_property PACKAGE_PIN	K16		[get_ports { io_vga_blue[3] }];

set_property IOSTANDARD		LVCMOS33	[get_ports { io_vga_red[0] }];
set_property IOSTANDARD		LVCMOS33	[get_ports { io_vga_red[1] }];
set_property IOSTANDARD		LVCMOS33	[get_ports { io_vga_red[2] }];
set_property IOSTANDARD		LVCMOS33	[get_ports { io_vga_red[3] }];
set_property IOSTANDARD		LVCMOS33	[get_ports { io_vga_blue[0] }];
set_property IOSTANDARD		LVCMOS33	[get_ports { io_vga_blue[1] }];
set_property IOSTANDARD		LVCMOS33	[get_ports { io_vga_blue[2] }];
set_property IOSTANDARD		LVCMOS33	[get_ports { io_vga_blue[3] }];

## Pmod Header JB
set_property PACKAGE_PIN	J17		[get_ports { io_vga_horizontalSync }];
set_property PACKAGE_PIN	J18		[get_ports { io_vga_verticalSync }];
set_property PACKAGE_PIN 	E15		[get_ports { io_vga_green[0] }];
set_property PACKAGE_PIN	E16		[get_ports { io_vga_green[1] }];
set_property PACKAGE_PIN 	D15		[get_ports { io_vga_green[2] }];
set_property PACKAGE_PIN 	C15		[get_ports { io_vga_green[3] }];

set_property IOSTANDARD		LVCMOS33	[get_ports { io_vga_horizontalSync }];
set_property IOSTANDARD		LVCMOS33	[get_ports { io_vga_verticalSync }];
set_property IOSTANDARD		LVCMOS33	[get_ports { io_vga_green[0] }];
set_property IOSTANDARD		LVCMOS33	[get_ports { io_vga_green[1] }];
set_property IOSTANDARD		LVCMOS33	[get_ports { io_vga_green[2] }];
set_property IOSTANDARD		LVCMOS33	[get_ports { io_vga_green[3] }];

set_property PACKAGE_PIN	D9		[get_ports { reset }];
set_property IOSTANDARD		LVCMOS33	[get_ports { reset }];

set_property PACKAGE_PIN 	V15		[get_ports { io_spi_ss }];
set_property PACKAGE_PIN 	U16		[get_ports { io_spi_mosi }];
set_property PACKAGE_PIN 	P14		[get_ports { io_spi_sck }];

set_property IOSTANDARD 	LVCMOS33 	[get_ports { io_spi_ss }];
set_property IOSTANDARD 	LVCMOS33 	[get_ports { io_spi_mosi }];
set_property IOSTANDARD 	LVCMOS33	[get_ports { io_spi_sck }];
