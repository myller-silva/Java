package atvs.entregaveis.calculo_de_expressao.atividade;

public class Fila {
  public Node primeiro; //front
  public Node ultimo; //rear
  int length;

  public Fila(){
    this.primeiro=null;
    this.ultimo=null;
    this.length=0;
  }

  public boolean isEmpty() {
    return (this.primeiro==null);
  }

  public boolean isFull() {
    try{
      new Node();
      return false;
    }catch(Exception e){
      System.out.println(e);
      return true;
    }
  }

  public boolean push(String item) {
    if(isFull()){
      return false;
    }
    Node newNode = new Node();
    newNode.value=item;
    newNode.next=null;
    if(this.primeiro==null){
      this.primeiro=newNode;
    }else{
      this.ultimo.next = newNode;
    }
    this.ultimo=newNode;
    length++;
    return true;
  }

  public boolean push(char c) {
    return this.push(String.format("%c", c));
  }

  public boolean push(String item, int index) { //teste
    if(isFull()) return false;
    if(isEmpty() && index!=0) return false;
    if(index>length) return false;
    if(index<0) return false;
    if(index==length){
      return this.push(item);
    }
    if(index==0 && this.isEmpty()) {
      return push(item);    
    }
    if(index==0){
      Node n = new Node();
      n.value = item;
      n.next = this.primeiro;
      this.primeiro = n;
      length++;
      return true;
    }

    Node no = this.primeiro;
    for (int i = 0; i < index-1; i++) {
      no=no.next;
    }
    Node temp = new Node();

    temp.value=item;
    temp.next=no.next;
    no.next=temp;

    length++;
    return true;
  }



  public boolean pop() {
    this.primeiro=this.primeiro.next;
    length--;
    return false;
  }

  @Override
  public String toString() {
    Node temp = primeiro;
    String str="";
    // str = "[ ";
    
    while(temp!=null){
      str += temp.value+" ";
      temp = temp.next;
    }

    // str+="]";
    return str;
  }
  
}
