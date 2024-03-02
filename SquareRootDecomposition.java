import java.util.*;

class SquareRootDecomposition
{
  int N;
  int arr[];
  int block[];
  int blockSize;
  SquareRootDecomposition(int arr[])
  {
    this.arr = arr;
    this.N = arr.length;
    this.blockSize = (int)Math.sqrt(N);
    this.block = new int[this.blockSize+1];
    preprocess();
  }
  

  void update(int idx, int val)
  {
    int blockNumber = idx/this.blockSize;
    block[blockNumber] += val-arr[idx];  
    arr[idx] = val;
  }


  int sumQuery(int l, int r)
  {
    int sum = 0;
    while(l<r && l%this.blockSize!=0 && l!=0)
    {
      sum += arr[l++];
    }
    while (l + this.blockSize - 1 <= r)
    {
        sum += block[l / this.blockSize];
        l += this.blockSize;
    }
    while(l<=r)
    {
      sum += arr[l++];
    }

    return sum;

  }


  void preprocess()
  {
    int blockSum = 0;
    int k = 0;
    for(int i=0;i<this.N;i++)
    {
      if(i%this.blockSize==0 && i!=0)
      {
        block[k++] = blockSum;
        blockSum = 0;
      }
      blockSum += this.arr[i];
    }
    block[k] = blockSum;
  }


  void printArray()
  {
    System.out.println();
    for(int i=0;i<this.N;i++)
    {
      System.out.print(this.arr[i]+" ");
    }
    System.out.println();
  }
  
  void printBlock()
  {
    System.out.println();
    for(int i=0;i<=this.blockSize;i++)
    {
      System.out.print(this.block[i]+" ");
    }
    System.out.println();
  }


  public static void main(String args[])
  {

    int N = 19;
    int arr[] = new int[N];
    for(int i=0;i<N;i++)
    {
      arr[i]=i+2;
    }

    SquareRootDecomposition ob=new SquareRootDecomposition(arr);
    ob.printArray();
    ob.printBlock();
    ob.update(0, 5);
    ob.printArray();
    ob.printBlock();
    ob.update(7,0);
    ob.printArray();
    ob.printBlock();
    System.out.println("\nBlock Size : "+ob.blockSize);
    int sum = ob.sumQuery(0, N-1);
    System.out.println("\nSum = "+sum);
    sum = ob.sumQuery(1, 6);
    System.out.println("\nSum = "+sum);


  }
}
