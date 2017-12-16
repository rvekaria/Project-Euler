package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        //problem1();
        //problem2();
        //problem2alternate();
        //problem3(600851475143L);
        //problem4(3);
        //problem5(20);
        //problem6(100);
        //problem7(10001);
        //problem8(13);
        //problem8BruteForce(13);/This is pretty much the same time as problem8
        //problem9(1000);
        //problem10(100000);
        //problem10A(2000000);
        //problem10B(2000000);
        problem10Opt(2000000);

    }//main

    static void problem1() {
        int mult1 = 3;
        int mult2 = 5;
        int limit = 1000;
        int sum = 0;
        for (int i = 0; i < limit; i++) {
            if ((i % mult1 == 0) || (i % mult2 == 0)) {
                sum += i;
            }//if
        }//for
        System.out.println("The sum of all the multiples of " + mult1 + " and " + mult2 + " which are below " + limit + " is " + sum);
    }//problem1 - sum of multiples of 3 or 5

    static void problem2() {
        int n = 0;
        int limit = 4000000;
        int sum = 0;
        int ans = fib(0);
        do {
            if (ans % 2 == 0) {
                //System.out.println("Adding " + ans + " to " + sum);
                sum += ans;
            }//if
            n++;
            ans = fib(n);
        } while (ans < limit);
        System.out.println("The sum of the even-valued Fibonacci terms less than " + limit + " is " + sum);
    }//problem2 - sum of even Fibonacci numbers below 4000000

    static void problem2alternate() {
        int n = 0;
        int limit = 4000000;
        int sum = 0;
        int ans = fib(0);
        do {
            sum += ans;
            //System.out.println("Adding " + ans + " to " + sum);
            n += 3;//know that every third value of Fib is even
            ans = fib(n);
        } while (ans < limit);
        System.out.println("The sum of the even-valued Fibonacci terms less than " + limit + " is " + sum);
    }//problem2alternate

    static int fib(int n) {
        //base case
        if (n == 0 || n == 1) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }//fib 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610

    static void problem3(long n) {
        long num = n;
        if (isPrime(n)) {
            System.out.println("The largest prime factor of " + n + " is " + n);
        } else {
            long maxPrime = 0;
            for (long factor = 2; factor <= n / 2; factor++) {
                if (n % factor == 0 && isPrime(factor)) {//if n is a prime factor
                    if (factor > maxPrime) {
                        maxPrime = factor;
                    }//if
                    do {
                        n /= factor;
                    } while (n % factor == 0);
                }//if
                System.out.println("Checking factor " + factor + " for " + n);
                System.out.println("Largest prime so far is " + maxPrime);
            }//for
            System.out.println("The largest prime factor of " + num + " is " + n);
        }
    }//problem3 - largest prime factor of a number

    static boolean isPrime(long n) {
        boolean foundFactor = false;
        long testFactor = 2;
        while (!foundFactor && testFactor <= (n / 2)) {
            if (n % testFactor == 0) {
                foundFactor = true;
            }
            testFactor++;
        }//while
        if (n == 0 || n == 1 || foundFactor) {
            return false;
        } else {
            return true;
        }//if-else
    }//isPrime

    static void problem4(int digits) {
        int max = (int) (Math.pow(10, digits) - 1);
        int maxPal = 0;
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i <= max; i++) {
            for (int j = i; j <= max; j++) {
                int ans = i * j;
                if (isPalindrome(ans) && ans > maxPal) {
                    maxPal = ans;
                    num1 = i;
                    num2 = j;
                }//if
            }//for - inner
        }//for - outer
        if (maxPal == 0) {
            System.out.println("There are no palindromes made from the product of two " + digits + " numbers.");
        } else {
            System.out.println("The largest palindrome made from the product of two " + digits + " numbers is " + maxPal + " = " + num1 + "x" + num2);
        }
    }//problem 4 - largest palindrome made from the product of two n-digit numbers

    static boolean isPalindrome(int n) {
        ArrayList<Character> digits = intToArrayList(n);
        ArrayList<Character> reversedDigits = intToArrayList(n);
        Collections.reverse(reversedDigits);

        String number = digits.toString();
        String reversedNumber = reversedDigits.toString();

        if (number.equals(reversedNumber)) {
            return true;
        } else {
            return false;
        }//if-else
    }//is Palindrome

    static ArrayList<Character> intToArrayList(int n) {
        String nString = Integer.toString(n);
        char[] nArray = nString.toCharArray();
        ArrayList<Character> nArrayList = new ArrayList<>();
        for (char c : nArray) {
            nArrayList.add(c);
        }//for - add all elements of char[] into ArrayList
        return nArrayList;
    }//intToArrayList

    static void problem5(int max) {
        int maxPrime = 0;
        for (int i = 1; i <= max; i++) {
            if (isPrime(i)) {
                maxPrime = i;
            }//if
        }//for - count the number of primes there are in the range and find the largest prime
        // then you know all prime factors found will be less than this max
        int[] highestPrimePowersArray = new int[maxPrime + 1];
        for (int i = 1; i <= max; i++) {
            int[] occurrences = new int[maxPrime + 1];
            ArrayList<Integer> primeFactors = getPrimeFactors(i);
            for (int p : primeFactors) {
                occurrences[p]++;
            }//for - get an array of the amount of each prime factor e.g occurrences[5] will give the number of 5's in the prime factorisation
            /*//DEBUGGY
            System.out.print("Occurrences for " + i + ": [");
            for (int o : occurrences) {
                System.out.print(o + ", ");
            }//debuggy see what's in occurrences
            System.out.println("]");
            //END OF DEBUGGY*/
            for (int j = 2; j <= maxPrime; j++) {
                if (occurrences[j] > highestPrimePowersArray[j]) {
                    highestPrimePowersArray[j] = occurrences[j];
                }
            }//for - find the highest power of each prime factor

            /*//DEBUGGY
            System.out.print("Highest powers so far up to number " + i + ": [");
            for (int h : highestPrimePowersArray) {
                System.out.print(h + ", ");
            }//debuggy see what's in highest powers
            System.out.println("]");
            //END OF DEBUGGY*/
        }//for each number up to max, create an ArrayList of prime factors
        int LCM = 1;
        for (int k = 2; k <= maxPrime; k++) {
            LCM *= Math.pow(k, highestPrimePowersArray[k]);
        }//get the product of prime factors
        System.out.println("The LCM of the numbers from 1 to " + max + " is " + LCM);
    }//problem5 - LCM of set of numbers from 1 to max

    static ArrayList<Integer> getPrimeFactors(int n) {
        ArrayList<Integer> primeFactors = new ArrayList<>();
        for (int factor = 1; factor <= n; factor++) {
            if (isPrime(factor) && n % factor == 0) {
                do {
                    primeFactors.add(factor);
                    n /= factor;
                } while (n % factor == 0);
            }//if
        }//for
        return primeFactors;
    }//getPrimeFactors

    static void problem6(int range) {
        int ans = squareOfSum(range) - sumOfSquares(range);
        System.out.println("The difference between the sum of the squares and square of the sum of the first " + range +
                " natural numbers is " + ans);
    }//problem6

    static int sumOfSquares(int range) {
        int sum = 0;
        for (int i = 1; i <= range; i++) {
            sum += i * i;
        }//for
        return sum;
    }//sumOfSquares

    static int squareOfSum(int range) {
        int sum = 0;
        for (int i = 1; i <= range; i++) {
            sum += i;
        }//for
        return sum * sum;
    }//sumOfSquares

    static void problem7(int pos) {
        int count = 3;
        int num = 5;
        while (count != pos) {
            num += 2;
            if (isPrime(num)) {
                count++;
            }//if
        }//while
        System.out.println(num);
    }//problem7 - 10001st prime number

    static void problem8(int adj) {
        String num1000Digits =
                "73167176531330624919225119674426574742355349194934" +
                        "96983520312774506326239578318016984801869478851843" +
                        "85861560789112949495459501737958331952853208805511" +
                        "12540698747158523863050715693290963295227443043557" +
                        "66896648950445244523161731856403098711121722383113" +
                        "62229893423380308135336276614282806444486645238749" +
                        "30358907296290491560440772390713810515859307960866" +
                        "70172427121883998797908792274921901699720888093776" +
                        "65727333001053367881220235421809751254540594752243" +
                        "52584907711670556013604839586446706324415722155397" +
                        "53697817977846174064955149290862569321978468622482" +
                        "83972241375657056057490261407972968652414535100474" +
                        "82166370484403199890008895243450658541227588666881" +
                        "16427171479924442928230863465674813919123162824586" +
                        "17866458359124566529476545682848912883142607690042" +
                        "24219022671055626321111109370544217506941658960408" +
                        "07198403850962455444362981230987879927244284909188" +
                        "84580156166097919133875499200524063689912560717606" +
                        "05886116467109405077541002256983155200055935729725" +
                        "71636269561882670428252483600823257530420752963450";
        String[] digitsSplitBy0 = num1000Digits.split("0");
        //[9, 7, 8, 1, 7, 9, 7, 7, 8, 4, 6, 1, 7]
        long maxProduct = 0;
        int[] productDigits = new int[adj];
        for (int i = 0; i < digitsSplitBy0.length; i++) {//for each element in the array (which are numbers w/o zero)
            if (digitsSplitBy0[i].length() >= adj) {//Only act on elements which have at least 13 digits in them, o/w move onto next element
                char[] digitsOfElement = digitsSplitBy0[i].toCharArray();//the i-th element is put into a char array
                for (int k = 0; k <= digitsOfElement.length - adj; k++) {//find product of next group of 13 numbers in that element
                    long product = 1;
                    for (int j = k; j < adj + k; j++) {
                        product *= (digitsOfElement[j] - 48);//need to subtract 48 because it's using the ascii value of the digits in the char array
                    }//for j
                    if (product > maxProduct) {
                        maxProduct = product;
                        for (int j = 0; j < adj; j++) {
                            productDigits[j] = digitsOfElement[j + k] - 48;
                        }//for - adding elements that made the maxProduct to an array
                    }//if
                }//for k
            }//if
        }//for i
        System.out.println("The greatest product of " + adj + " adjacent digits in the 1000 digit number is " + maxProduct);
        System.out.println("The digits that multiplied to give this are " + Arrays.toString(productDigits));
    }//problem8 - find the greatest product of 'adj' adjacent digits in a 1000 digit number

    static void problem8BruteForce(int adj) {
        String num1000Digits =
                "73167176531330624919225119674426574742355349194934" +
                        "96983520312774506326239578318016984801869478851843" +
                        "85861560789112949495459501737958331952853208805511" +
                        "12540698747158523863050715693290963295227443043557" +
                        "66896648950445244523161731856403098711121722383113" +
                        "62229893423380308135336276614282806444486645238749" +
                        "30358907296290491560440772390713810515859307960866" +
                        "70172427121883998797908792274921901699720888093776" +
                        "65727333001053367881220235421809751254540594752243" +
                        "52584907711670556013604839586446706324415722155397" +
                        "53697817977846174064955149290862569321978468622482" +
                        "83972241375657056057490261407972968652414535100474" +
                        "82166370484403199890008895243450658541227588666881" +
                        "16427171479924442928230863465674813919123162824586" +
                        "17866458359124566529476545682848912883142607690042" +
                        "24219022671055626321111109370544217506941658960408" +
                        "07198403850962455444362981230987879927244284909188" +
                        "84580156166097919133875499200524063689912560717606" +
                        "05886116467109405077541002256983155200055935729725" +
                        "71636269561882670428252483600823257530420752963450";
        char[] digits1000 = num1000Digits.toCharArray();
        long maxProduct = 0;
        for (int j = 0; j <= num1000Digits.length() - adj; j++) {
            long product = 1;
            for (int i = j; i < j + adj; i++) {
                product *= (digits1000[i] - 48);
            }//for inner - taking product of 13 digits
            if (product > maxProduct) {
                maxProduct = product;
            }//if
        }//for outer - shuffling along the 1000 digits to multiply the next set of 13 digits
        System.out.println("The greatest product of " + adj + " adjacent digits in the 1000 digit number is " + maxProduct);
        //System.out.println("The digits that multiplied to give this are " + Arrays.toString(productDigits));
    }//problem8BruteForce - find the greatest product of 'adj' adjacent digits in a 1000 digit number

    static void problem9(int sum) {
        double c;
        for (double a = 1; a < sum - 1; a++) {
            for (double b = 1; b <= a; b++) {
                c = Math.sqrt(a * a + b * b);
                if (a + b + c == sum) {
                    System.out.println((int) a + " + " + (int) b + " + " + (int) c + " = " + sum);
                    int product = (int) (a * b * c);
                    System.out.println("The product abc" + " = " + product);
                }//if
            }//for - b, goes up to the value of a
        }//for - a, goes up to 2 less than the value of the sum
    }//problem9 - Find Pythagorean triplet which sums to "sum"

    static void problem10(int maxPrime) {
        long sum = 2;
        long skip = 2;//update this everytime a prime is found and skip multiples of that prime
        for (int i = 1; i < maxPrime; i += skip) {

            System.out.println("Checking if " + i + " is prime");
            if (isPrime(i)) {
                sum += i;
                System.out.println("Sum so far is " + sum);
            }//if
        }//for
        System.out.println("The sum of all the primes below " + maxPrime + " is " + sum);
    }//problem10 - summation of primes, find a prime and add it to a running total

    static void problem10A(int maxPrime) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(2);
        for (int i = 3; i <= maxPrime; i += 2) {
            numbers.add(i);
        }//for-add all numbers to array

        for (int i = 1; i <= numbers.size() / 2; i++) {
            int multiple = numbers.get(i);
            System.out.println("Removing multiples of " + multiple);
            for (int j = i + 1; j < numbers.size(); j++) {
                System.out.println("Checking if " + j + " is a multiple of " + multiple);
                if (numbers.get(j) % multiple == 0) {
                    numbers.remove(j);
                }//if
            }//for
        }//for
        long sum = 0;
        for (int k = 0; k < numbers.size(); k++) {
            sum += numbers.get(k);
            System.out.println("Sum so far is " + sum);
        }
        System.out.println("The sum of all the primes below " + maxPrime + " is " + sum);
    }//problem10A - summation of primes, remove multiples of numbers, add remaining numbers together

    static void problem10B(int maxPrime) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(2);
        for (int i = 3; i <= maxPrime; i += 2) {
            numbers.add(i);
        }//for-add all numbers to array, [2, 3, 5, 7, 9]

        for (int i = 1; i <= numbers.size() / 2; i++) {//starts with multiples of 3
            int multiple = numbers.get(i);
            System.out.println("Removing multiples of " + multiple);
            while (multiple < maxPrime) {
                multiple += numbers.get(i);
                int removeIndex = numbers.indexOf(multiple);//index is -1 if the number is not in the ArrayList
                if (removeIndex != -1) {
                    System.out.println("Removing " + multiple);
                    numbers.remove(removeIndex);
                }//if
            }//while-removing multiples of the prime
        }//for
        long sum = 0;
        for (int k = 0; k < numbers.size(); k++) {
            sum += numbers.get(k);
            System.out.println("Sum so far is " + sum);
        }
        System.out.println("The sum of all the primes below " + maxPrime + " is " + sum);
    }//problem10B - summation of primes, remove multiples of numbers by just adding the multiples onto previous multiple

    static void problem10Opt(int limit) {
        int crossLimit = (int) Math.sqrt(limit + 1);//only need to go up to square root of max prime)
        boolean[] sieve = new boolean[limit + 1];
        Arrays.fill(sieve, true);//say all numbers in the sieve are present. Then cross out multiples (set to false)
        for (int n = 4; n < limit + 1; n += 2) {
            sieve[n] = false;
        }//for - cross out all even numbers
        for (int n = 3; n <= crossLimit; n += 2) {
            if (sieve[n]) {
                for (int m = n * n; m < limit + 1; m += 2 * n) {
                    sieve[m] = false;
                    System.out.println("Crossing out multiples of " + n + ": " + m);
                }//for - going up in steps of 2n because multiples of 2 have already been crossed out
                //starting at n^2 because all previous numbers will have been crossed out
            }//if the number hasn't been crossed out, ie it is a prime, then cross out its multiples
        }//for
        int sum = 2;
        for (int n = 3; n < limit + 1; n += 2) {
            if (sieve[n]) {
                sum += n;
            }//if
        }//for - add up all numbers not crossed out
        System.out.println("Sum of primes below " + limit + " is " + sum);
    }//problem10Opt - Using PDF for more efficient method

}//Main class
