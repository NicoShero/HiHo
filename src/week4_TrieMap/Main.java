package week4_TrieMap;
import java.util.*;

class Main{
private static final int HASH_SIZE = 26;//节点长度最大为26，和字母个数相同
    public static void main(String[] args) {
        new Main().go();//初始化启动函数
    }
    public void go() {//启动函数
        TrieGraph tg = new TrieGraph();//构造Trie树
        Scanner sc = new Scanner(System.in);
        try {
            int n = sc.nextInt();
            
            while (n-- > 0) {
                String s = sc.next();
                tg.addWord(s);//想Trie书中添加单词节点
            }
            tg.buildGraph();
            String text = sc.next();//输入"文章"，判断是否有Trie树中的单词
            if (tg.query(text)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public class Node {
        public Node[] next = new Node[HASH_SIZE];
        public Node post = null;
        public boolean isEnd = false;
        
        private char val;
        public Node par;
        
        public Node(char val, Node par) {
            this.val = val;
            this.par = par;
        }
        
        public Node() {
            this.val = '\0';
            this.par = this;
        }
        
        public void setPostRoot(Node root) {
            post = root;
        }
        
        public void setPost() {
            post = par.post.next[this.val - 'a'];
        }
        
        public Node getNext(char c) {
            Node next = this.next[c - 'a'];
            if (next == null) {
                next = this.next[c - 'a'] = new Node(c, this);
            }
            return next;
        }
        
        public void setNext(int i) {
            this.next[i] = post.next[i];
        }
    }
    
    public class TrieGraph {
        private Node root = new Node();
        
        public void addWord(String word) {
            Node n = root;
            for (int i = 0; i < word.length(); ++i) {
                n = n.getNext(word.charAt(i));
            }
            n.isEnd = true;
        }
        
        public void buildGraph() {
            Queue<Node> q = new LinkedList<Node>();
            root.setPostRoot(root);
            for (int i = 0; i < HASH_SIZE; ++i) {
                if (root.next[i] == null) {
                    root.next[i] = root;
                } else {
                    q.add(root.next[i]);
                }
            }
            while (!q.isEmpty()) {
                Node n = q.remove();
                if (n.par == root) {
                    n.setPostRoot(root);
                } else {
                    n.setPost();
                }
                
                for (int i = 0; i < HASH_SIZE; ++i) {
                    if (n.next[i] == null) {
                        n.setNext(i);
                    } else {
                        q.add(n.next[i]);
                    }
                }
            }
        }
        
        public boolean query(String word) {
            Node n = root;
            for (int i = 0; i < word.length(); ++i) {
                n = n.getNext(word.charAt(i));
                if (n.isEnd == true) {
                    return true;
                }
            }
            return false;
        }
    }
}
