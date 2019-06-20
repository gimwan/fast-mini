package com.fast.util;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 伪随机数（线性同余法）
 * @author J
 *
 */
public class Random {
	private final AtomicLong seed;
	private final static long multiplier = 0x5DEECE66DL;
	private final static long addend = 0xBL;
	private final static long mask = (1L << 48) - 1;
	private static volatile long seedUniquifier = 8682522807148012L;
	
	public void srandSeed(long t){
        this.seed.set(t);
	}
	
	public Random(){
		 this(++seedUniquifier + System.nanoTime());
	}
	
	public Random(long seed) {
        this.seed = new AtomicLong(0L);
        srandSeed(seed);
    }
	
	int random(int bits){
		long oldseed = seed.get();
		long nextseed = (oldseed * multiplier + addend) & mask;
		return (int)(nextseed >>> (48 - bits));
	}
	
	public static void main(String[] args) {
		Random r = new Random();
		Date now = new Date();
		r.srandSeed(now.getTime());
		System.out.println(r.random(24));
	}
	
	public static int number() {
		Random r = new Random();
		Date now = new Date();
		r.srandSeed(now.getTime());
		return r.random(24);
	}
}
