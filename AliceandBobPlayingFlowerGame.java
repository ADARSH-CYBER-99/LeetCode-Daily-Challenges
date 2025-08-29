// Date : 29-08-2025
class Solution {
    public long flowerGame(int n, int m) {
        long oddsN = (n + 1L) / 2; // odd numbers in 1..n
        long evensN = n / 2; // even numbers in 1..n
        long oddsM = (m + 1L) / 2; // odd numbers in 1..m
        long evensM = m / 2; // even numbers in 1..m

        return oddsN * evensM + evensN * oddsM;
    }
}
