
CC = gcc
CXX = g++

INC += -I./ \
	-I./COMMON \
	-I./UTIL \
	-I./UTIL/assert \
	-I./UTIL/itti \
	-I./UTIL/config \
	-I./UTIL/log \
	-I./UTIL/telnet \
	-I./openair1/COMMON \
	-I./openair1/L1 \
	-I./openair2/COMMON \
	-I./openair2/L2 \
	-I./openair3/COMMON \
	-I./openair3/L3 \
	-I./openair3/SCTP \
	-I./openair3/TCP \
	-I./openair3/UDP \
	-I./openair3/NGAP \
	-I./openair3/S1AP \
	-I./cmake_targets/build_dir/build/CMakeFiles/S1AP_R15 \
	-I./target/COMMON 
	

DEFS += -DASN_DISABLE_OER_SUPPORT

LIBS += -pthread -lm -lsctp -lz -ldl 

FLAGS += -pipe -std=gnu99 -Wall -Wstrict-prototypes -fno-strict-aliasing -rdynamic -funroll-loops -Wno-packed-bitfield-compat -fPIC -Wall -g3 -gdwarf-2 -O0

CFLAGS += -c -g 
CXXFLAGS += -c -g

CXX_SRC += $(wildcard *.cpp ./UTIL/itti/*.cpp)

S1AP_ASN1_C_SRC = $(wildcard ./cmake_targets/build_dir/build/CMakeFiles/S1AP_R15/*.c)

C_SRC = $(wildcard ./target/*.c \
	./target/COMMON/*.c \
	./UTIL/log/*.c \
	./UTIL/telnet/*.c \
	./openair1/L1/*.c \
	./openair2/L2/*.c \
	./openair3/L3/*.c \
	./openair3/SCTP/*.c \
	./openair3/TCP/*.c \
	./openair3/UDP/*.c \
	./openair3/NGAP/*.c \
	./openair3/S1AP/*.c \
	./cmake_targets/build_dir/build/CMakeFiles/S1AP_R15/*.c)

SRC_OBJS = $(C_SRC:.c=.o) $(CXX_SRC:.cpp=.o)

TARGET = main

all:$(TARGET)

$(TARGET):clean $(SRC_OBJS)
	$(CXX) $(FLAGS) $(INC) -o $@ $(SRC_OBJS) $(LIBS) $(DEFS)

%.o:%.c
	$(CC) -o $@  $(CFLAGS) $< $(INC) $(LIBS) $(DEFS)

%.o:%.cpp
	$(CXX) -o $@  $(CXXFLAGS) $< $(INC) $(LIBS) $(DEFS)

clean:
	rm -f $(SRC_OBJS)