class Solution(object):

    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """

        if x < 0:
            return False

        x_str = str(x)
        s_len = len(x_str)

        if s_len == 1:
            return True

        for i in range(0, s_len):
            if x_str[i] != x_str[s_len-1-i]:
                return False

        return True


def run(x):
    s = Solution()
    print s.isPalindrome(x)
