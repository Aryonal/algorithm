class Solution(object):
    def diffWaysToCompute(self, input):
        """
        :type input: str
        :rtype: List[int]
        """
        lst = self.compute(input)
        return lst

    def compute(self, inputs):
        lst = []
        operator = [i for i in xrange(len(inputs)) if inputs[i] in '+-*']
        if len(operator) == 0:
            lst.append(int(inputs))
            return lst
        for x in operator:
            ls1 = self.compute(inputs[0:x])
            ls2 = self.compute(inputs[x+1:])
            for i in ls1:
                for j in ls2:
                    if inputs[x] == '+':
                        lst.append(int(i)+int(j))
                    elif inputs[x] == '-':
                        lst.append(int(i)-int(j))
                    else:
                        lst.append(int(i)*int(j))
                        
        return lst


if __name__ == '__main__':
    #str = 'abcdef'
    #print str[0:10]
    s = Solution()
    lst = s.diffWaysToCompute('2*3-4*5')
    print(lst)
