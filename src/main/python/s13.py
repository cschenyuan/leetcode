class Solution(object):
    def romanToInt(self, s):
        """
        :type s: str
        :rtype: int
        """

        D = {
            'I': 1,
            'V': 5,
            'X': 10,
            'L': 50,
            'C': 100,
            'D': 500,
            'M': 1000,
            'IV': 4,
            'IX': 9,
            'XL': 40,
            'XC': 90,
            'CD': 400,
            'CM': 900
        }

        number = 0
        s_len = len(s)
        i = 0
        while i < s_len:
            if i+1 < s_len and D.has_key(s[i] + s[i+1]):
                number += D[s[i] + s[i+1]]
                i += 2
            else:
                number += D[s[i]]
                i += 1

        return number


def run(s):
    print Solution().romanToInt(s)
