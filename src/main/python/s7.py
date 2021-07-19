class Solution(object):

    def reverse(self, x):
        """
        :type x: int
        :rtype: int
        """

        if x == 0:
            return 0

        reversed_x = 0
        s = str(abs(x))
        s_len = len(s)
        head_zero_proccessed = False
        for i in range(0, s_len):
            s_idx = s[s_len-1-i]
            # print i, s_idx
            if s_idx != 0 or head_zero_proccessed:
                reversed_x = reversed_x * 10 + int(s_idx)
            else:
                head_zero_proccessed = True
        if x < 0:
            if (- reversed_x) < -(2 << 30):
                return 0
            else:
                return - reversed_x
        else:
            if reversed_x > ((2 << 30) - 1):
                return 0
            else:
                return reversed_x
