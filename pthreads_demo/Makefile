# Makefile for building demo applications.
# by Brian Fraser

# Edit this file to compile extra C files into their own programs.
#TARGETS= sum_on_thread sum_on_many_threads sum_malloc
TARGETS= sum_malloc

CROSS_TOOL = 
CC_CPP = $(CROSS_TOOL)g++
CC_C = $(CROSS_TOOL)gcc

CFLAGS = -Wall -g -std=c99 -Werror -pthread

all: clean $(TARGETS)

$(TARGETS):
	$(CC_C) $(CFLAGS) $@.c -o $@ 

clean:
	rm -f $(TARGETS)