import java.util.*;

public class Solution<Key extends Comparable<Key>, Value> {
    private Node root; 
    private int size = 0;        

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
         // number of nodes in subtree

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public Solution() {
        root = null;
    }

    /**
     * Returns true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        if (size()== 0) {
            return true;  
        }
        return false;
       
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return size;
    }
    /**
     * Does this symbol table contain the given key?
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}

    /**
     * Returns the value associated with the given key.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if(key == null){
            throw new IllegalArgumentException("argument to get value() is null");
        }
        Node tempNode = root;
        do{
            int cmp = key.compareTo(tempNode.key);
            if(cmp < 0){
                tempNode = tempNode.left;
            }
            else if(cmp > 0){
                tempNode = tempNode.right;
            }
        }
        while(tempNode.key != key);
        if(tempNode.key == key){
        }
        return tempNode.val;
    }
    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        Node newNode = new Node(key,val);
        if(root == null){
            root = newNode;
        }
        else{
            Node tempNode = root;
            Node top;
            do{
                top = tempNode;
                int cmp = key.compareTo(tempNode.key);
                if(cmp < 0){
                    tempNode = tempNode.left;
                    if(tempNode == null)
                    {
                        top.left = newNode;
                        size = size + 1;
                        return;
                    }
                    else if(tempNode.key == key)
                    {
                        tempNode.val = val;
                        return;
                    }
                }
                else if(cmp > 0){
                    tempNode = tempNode.right;
                    if(tempNode == null){
                        top.right = newNode;
                        size = size + 1;
                        return;
                    }
                    else if(tempNode.key == key)
                    {
                        tempNode.val = val;
                        return;
                    }
                }
            }
            while(true);
        }
        size = size + 1;
    }
     public Key min() {
        if(isEmpty()){
       		throw new NoSuchElementException("There is no element in the tree");
       }
     
        else{
            Node tempNode = root;
           while(tempNode.left != null);
           {
                tempNode = tempNode.left;
            }
            return tempNode.key;
        }
    } 

    public Key max() { 
         if(isEmpty()){
       		throw new NoSuchElementException("There is no element in the tree");
       }
       
        else{
            Node tempNode = root;
            while(tempNode.right != null);
            {
                tempNode = tempNode.right;
            }
            return tempNode.key;
        }
    } 
    public Key floor(Key key) {
       if(isEmpty()){
			throw new NoSuchElementException("calls floor() with empty symbol table");
		}
		Node tempNode = root;
		Node top = null;
		while(tempNode != null){
			top = tempNode;
			int cmp = key.compareTo(top.key);
			if(key == null){
				throw new IllegalArgumentException("calls floor() with a null key");
			}
			else if(cmp == 0){
				return top.key;
			}
			else if(cmp < 0){
				tempNode = top.left;
			}
			else if(cmp > 0){
				tempNode = top.right;
				int cmp1 = key.compareTo(tempNode.key);
				if(cmp1 < 0 ){
					return top.key;
				}
				else{
					tempNode = top.right;
				} 
			}
		}
		return top.key;
	}
     public Key select(Key key){
		if(isEmpty()){
			throw new NoSuchElementException("calls floor() with empty symbol table");
		}
		Node tempNode = root;
		Node top = null;
		while(tempNode != null){
			top = tempNode;
			int cmp = key.compareTo(top.key);
			if(key == null){
				throw new IllegalArgumentException("calls floor() with a null key");
			}
			if(cmp > 0){
				tempNode = top.right;
			}
			else if(cmp < 0){
				tempNode = top.left;
				int cmp1 = key.compareTo(top.key);
				if(cmp1 > 0 ){
					return tempNode.key;
				}
				else{
					tempNode = top.left;
				} 
			}
		}
		return top.key;
	}

   public Iterable<Key> keys(Key lo, Key hi) {
        if(lo==null || hi==null) throw new IllegalArgumentException("Keys() is null");

        Queue<Key> queue = new LinkedList<Key>();
        keys(root, queue, lo, hi);  
        return queue;
    } 

    private void keys(Node tempNode,Queue<Key> queue, Key lo, Key hi) { 
        if (tempNode== null) return;  
        
        Node temp = tempNode;  
        while (temp != null){  
      
            int cmp = temp.key.compareTo(hi);
            int cap1 = temp.key.compareTo(lo);
            if (temp.left == null){   
                if (cmp <= 0 && cap1 >= 0)  queue.offer(temp.key);
                temp = temp.right;

            }else{  
                Node temp1 = temp.left;
            
                while (temp1.right != null && temp1.right != temp)  
                    temp1 = temp1.right;  
        
                if (temp1.right == null){  
                    temp1.right = temp;  
                    temp = temp.left; 

                }else{  
                    temp1.right = null;    
                    if (cmp <= 0 && cap1 >= 0)  queue.offer(temp.key);  
                    temp = temp.right;  
                }  
            }  
        }   
    } 

   
    /* Run the program by giving the approriate command obtained from
    input files through input.txt files. The output should be displayed
    exactly like the file output.txt shows it to be.*/
   
    public static void main(String[] args) { 
        Solution<String, Integer> obj = new Solution<String, Integer>();
       obj.put("ABDUL",1);
        System.out.println(obj.get("ABDUL"));

        obj.put("HRITHIK",2);
        obj.put("SAI",3);
        obj.put("SAMAL",6);
        System.out.println(obj.get("SAI"));

        obj.put("TASHI",4);
        System.out.println(obj.size());
      // System.out.println(obj.min());
        System.out.println(obj.floor("HRITHIK"));
        System.out.println(obj.floor("HAHA"));
       // System.out.println(obj.select("HRITHIK"));

        for (String s : obj.keys("ABDUL","TASHI"))
            System.out.print(s+" ");
        System.out.println();

        obj.put("CHIMI",5);
        obj.put("SAMAL",4);
        System.out.println(obj.get("SAMAL"));
        obj.put("NIMA",7);
        System.out.println(obj.size());
        System.out.println(obj.get("CHIMI"));
        System.out.println(obj.floor("CHIMI"));
        obj.put("SONAM",8);

        for (String s : obj.keys("ABDUL","TASHI"))
            System.out.print(s+" ");
        System.out.println();

    
        
       
    }
}