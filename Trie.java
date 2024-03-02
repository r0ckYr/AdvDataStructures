import java.util.*;


class Node
{
  Node[] children=new Node[26];
  boolean isWord;
  Node()
  {
    isWord = false;
    for(int i=0;i<26;i++)
    {
      children[i] = null;
    }
  }
}


public class Trie
{
  Node root;
  Trie()
  {
    this.root=new Node(); 
  }


  void insert(String word)
  {
    int len=word.length();
    Node curr = root;
    for(int i=0;i<len;i++)
    {
      if(curr.children[word.charAt(i)-'a']==null)
        curr.children[word.charAt(i)-'a'] = new Node();
      curr = curr.children[word.charAt(i)-'a'];
    }
    if(curr.isWord==true)
      System.out.println("\nWord exists\n");
    else
      curr.isWord = true;    
  }


  void search(String word)
  {
    Node curr = root;
    int len=word.length();
    for(int i=0;i<len;i++)
    {
      if(curr.children[word.charAt(i)-'a']!=null)
        curr = curr.children[word.charAt(i)-'a'];
      else
      {
        System.out.println("\nWord Not Found\n");
        return;
      }
    }
    if(curr.isWord)
      System.out.println("\nWord Found\n");
    else
      System.out.println("\nWord Not Found\n");
  }
  
  
  boolean hasChildren(Node root)
  {
    for(int i=0;i<26;i++)
    {
      if(root.children[i]!=null)
        return true;
    }

    return false;
  }

  boolean deleteUtils(Node root, String word, int i, int N)
  {
    boolean toDelete=true;
    if(i<N)
      toDelete = deleteUtils(root.children[word.charAt(i)-'a'],word,i+1,N);
    
    
    if(i==N)
      root.isWord = false;
    
    if(toDelete==false)
      return false;
    
    if(!hasChildren(root) && !root.isWord)
    {
      root = null;
      return true;
    }
    return false;
  }


  void delete(String word)
  {
    
    int len=word.length();
    boolean f=deleteUtils(root, word, 0, len);
    System.out.println("Deleted("+f+") :"+word);
  }

  static void printMenu()
  {
    System.out.println("\n\nMenu:\n1. Insert word\n2. Search word\n3. Delete word\n4. Exit\n\n");
  }


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Trie ob=new Trie();
    int choice;
    String word;
    do {
      printMenu();
      System.out.print("\nEnter your choice :");
      choice = sc.nextInt();
      if(choice==1)
      {
        System.out.print("\nEnter a word :");
        word = sc.next();
        ob.insert(word);
      }
      else if(choice==2)
      {
        System.out.print("\nEnter a word :");
        word = sc.next();
        ob.search(word);
      }
      else if(choice==3)
      {
        System.out.print("\nEnter a word :");
        word = sc.next();
        ob.delete(word);
      }
      else if(choice==4)
      {
        break;
      }
      else
      {
        System.out.println("\nInvalid Choice\n");
      }
      
    } while (true);


  }
}
