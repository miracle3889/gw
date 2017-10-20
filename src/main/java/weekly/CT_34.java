package weekly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xudabiao on 2017/6/5.
 */
public class CT_34 {
    /**
     * 605. Can Place Flowers
     *
     Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

     Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

     Example 1:
     Input: flowerbed = [1,0,0,0,1], n = 1
     Output: True
     Example 2:
     Input: flowerbed = [1,0,0,0,1], n = 2
     Output: False
     Note:
     The input array won't violate no-adjacent-flowers rule.
     The input array size is in the range of [1, 20000].
     n is a non-negative integer which won't exceed the input array size.
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int cs = 0;
        int available = 0;
        while (cs<flowerbed.length) {
            if(flowerbed[cs]==1)
                cs+=2;
            else if (flowerbed[cs] == 0 && (cs + 1 == flowerbed.length || flowerbed[cs + 1] == 0)) {
                available++;
                cs+=2;
            }
            else
                cs+=3;
        }
        return n<=available;
    }
    /**
     * 606. Construct String from Binary Tree My SubmissionsBack To Contest
     User Accepted: 858
     User Tried: 906
     Total Accepted: 871
     Total Submissions: 1543
     Difficulty: Easy
     You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.

     The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.

     Example 1:
     Input: Binary tree: [1,2,3,4]
     1
     /   \
     2     3

     4

     Output: "1(2(4))(3)"

     Explanation: Originallay it needs to be "1(2(4)())(3()())",
     but you need to omit all the unnecessary empty parenthes/is pairs.
     And it will be "1(2(4))(3)".
     Example 2:
     Input: Binary tree: [1,2,3,null,4]
     1
     /   \
     2     3
     \
     4

     Output: "1(2()(4))(3)"

     Explanation: Almost the same as the first example,
     except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
     */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public String tree2str(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(sb,t);
        if(sb.length()==0)
            return sb.toString();
        return sb.toString().substring(1,sb.length()-1);
    }

    private void preOrderTraverse(StringBuilder sb, TreeNode t) {
        if(t==null)
            return;
        sb.append("(").append(t.val);
        if(t.left==null&&t.right!=null)
            sb.append("()");
        preOrderTraverse(sb,t.left);
        preOrderTraverse(sb,t.right);
        sb.append(")");
    }

    /**
     * 609. Find Duplicate File in System My SubmissionsBack To Contest
     User Accepted: 666
     User Tried: 704
     Total Accepted: 672
     Total Submissions: 1263
     Difficulty: Medium
     Given a list of directory info including directory path,
     and all the files with contents in this directory,
     you need to find out all the groups of duplicate files in the file system in terms of their paths.

     A group of duplicate files consists of at least two files that have exactly the same content.

     A single directory info string in the input list has the following format:

     "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"

     It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content, respectively) in directory root/d1/d2/.../dm. Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.

     The output is a list of group of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:

     "directory_path/file_name.txt"

     Example 1:
     Input:
     ["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
     Output:
     [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
     Note:
     No order is required for the final output.
     You may assume the directory name, file name and file content only has letters and digits, and the length of file content is in the range of [1,50].
     The number of files given is in the range of [1,20000].
     You may assume no files or directories share the same name in a same directory.
     You may assume each given directory info represents a unique directory. Directory path and file infos are separated by a single blank space.
     Follow up beyond contest:
     Imagine you are given a real file system, how will you search files? DFS or BFS ?
     If the file content is very large (GB level), how will you modify your solution?
     If you can only read the file by 1kb each time, how will you modify your solution?
     What is the time complexity of your modified solution? What is the most time consuming part and memory consuming part of it? How to optimize?
     How to make sure the duplicated files you find are not false positive?
     */
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String,List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] sdo = path.split(" ");
            String dir = sdo[0];
            for (int i = 1; i < sdo.length; i++) {
                String fileName = sdo[i].substring(0,sdo[i].indexOf('('));
                String content = sdo[i].substring(sdo[i].indexOf('('));
                List<String> list = map.get(content);
                if(list==null){
                    list = new ArrayList<>();
                    map.put(content,list);
                }
                list.add(dir+"/"+fileName);
            }
        }
        List<List<String>> lists = new ArrayList<>();
        for (String s : map.keySet())
            if(map.get(s).size()>1)
                lists.add(map.get(s));
        return lists;
    }

    /**
     * 591. Tag Validator
     My SubmissionsBack To Contest
     User Accepted: 78
     User Tried: 189
     Total Accepted: 82
     Total Submissions: 673
     Difficulty: Hard
     Given a string representing a code snippet, you need to implement a tag validator to parse the code and return whether it is valid. A code snippet is valid if all the following rules hold:

     The code must be wrapped in a valid closed tag. Otherwise, the code is invalid.
     A closed tag (not necessarily valid) has exactly the following format : <TAG_NAME>TAG_CONTENT</TAG_NAME>. Among them, <TAG_NAME> is the start tag, and </TAG_NAME> is the end tag. The TAG_NAME in start and end tags should be the same. A closed tag is valid if and only if the TAG_NAME and TAG_CONTENT are valid.
     A valid TAG_NAME only contain upper-case letters, and has length in range [1,9]. Otherwise, the TAG_NAME is invalid.
     A valid TAG_CONTENT may contain other valid closed tags, cdata and any characters (see note1) EXCEPT unmatched <, unmatched start and end tag, and unmatched or closed tags with invalid TAG_NAME. Otherwise, the TAG_CONTENT is invalid.
     A start tag is unmatched if no end tag exists with the same TAG_NAME, and vice versa. However, you also need to consider the issue of unbalanced when tags are nested.
     A < is unmatched if you cannot find a subsequent >. And when you find a < or </, all the subsequent characters until the next > should be parsed as TAG_NAME (not necessarily valid).
     The cdata has the following format : <![CDATA[CDATA_CONTENT]]>. The range of CDATA_CONTENT is defined as the characters between <![CDATA[ and the first subsequent ]]>.
     CDATA_CONTENT may contain any characters. The function of cdata is to forbid the validator to parse CDATA_CONTENT, so even it has some characters that can be parsed as tag (no matter valid or invalid), you should treat it as regular characters.
     Valid Code Examples:
     Input: "<DIV>This is the first line <![CDATA[<div>]]></DIV>"

     Output: True

     Explanation:

     The code is wrapped in a closed tag : <DIV> and </DIV>.

     The TAG_NAME is valid, the TAG_CONTENT consists of some characters and cdata.

     Although CDATA_CONTENT has unmatched start tag with invalid TAG_NAME, it should be considered as plain text, not parsed as tag.

     So TAG_CONTENT is valid, and then the code is valid. Thus return true.


     Input: "<DIV>>>  ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DIV>"

     Output: True

     Explanation:

     We first separate the code into : start_tag|tag_content|end_tag.

     start_tag -> "<DIV>"

     end_tag -> "</DIV>"

     tag_content could also be separated into : text1|cdata|text2.

     text1 -> ">>  ![cdata[]] "

     cdata -> "<![CDATA[<div>]>]]>", where the CDATA_CONTENT is "<div>]>"

     text2 -> "]]>>]"


     The reason why start_tag is NOT "<DIV>>>" is because of the rule 6.
     The reason why cdata is NOT "<![CDATA[<div>]>]]>]]>" is because of the rule 7.
     Invalid Code Examples:
     Input: "<A>  <B> </A>   </B>"
     Output: False
     Explanation: Unbalanced. If "<A>" is closed, then "<B>" must be unmatched, and vice versa.

     Input: "<DIV>  div tag is not closed  <DIV>"
     Output: False

     Input: "<DIV>  unmatched <  </DIV>"
     Output: False

     Input: "<DIV> closed tags with invalid tag name  <b>123</b> </DIV>"
     Output: False

     Input: "<DIV> unmatched tags with invalid tag name  </1234567890> and <CDATA[[]]>  </DIV>"
     Output: False

     Input: "<DIV>  unmatched start tag <B>  and unmatched end tag </C>  </DIV>"
     Output: False
     Note:
     For simplicity, you could assume the input code (including the any characters mentioned above) only contain letters, digits, '<','>','/','!','[',']' and ' '.
     * @param args
     */
    public static void main(String[] args) {
        String g = "abc(def)";
        System.out.println(g.substring(g.indexOf('('),g.lastIndexOf(')')));
    }
}
