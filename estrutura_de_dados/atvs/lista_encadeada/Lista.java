package atvs.lista_encadeada;

public class Lista {
  public Node primeiro, ultimo;
  public int length;

  public Lista(){
    this.primeiro=null;
    this.ultimo=null;
    this.length=0;
  }
  

  public boolean isEmpty() {
    return (this.primeiro==null);
  }
  
  public boolean isFull() {
    try{
      new Node(0);
      return false;
    }catch(Exception e){
      System.err.println(e);
      return true;
    }
  }

  public boolean remove(String start_or_end ) {
    switch (start_or_end) {
      case "start":
      case "s":
        return remove(0);
      case "end":
      case "e":
        return remove();
      default:
        return false;
    }
  }
  
  public boolean remove() {
    return remove(length-1);
  }

  public boolean remove(int index) {
    if( length==1 && index==0 ){
      this.primeiro=null;
      this.ultimo=null;
      this.length=0;
      return true;
    }
    if(isEmpty()) return false;
    if(index>length-1) return false;

    if(index==0){
      this.primeiro = this.primeiro.proximo;
      this.primeiro.anterior = null;
      length--;
      return true;
    }
    if(index==length-1){
      this.ultimo = this.ultimo.anterior;
      this.ultimo.proximo = null;
      length--;
      return true;
    }

    Node ant = this.primeiro;
    Node del = this.primeiro.proximo;
    int c=1;
    while(del!=null){
      if(c==index){
        Node prox = del.proximo;
        prox.anterior=ant;
        ant.proximo = prox;        
        length--;
        return true;
      }
      c++;
      ant=ant.proximo;
      del=del.proximo;
    }
    return false;
  }

  public boolean add(Node item, int posicao) {
    if(isFull())return false;
    if( posicao>length || posicao<0) return false;
    if(isEmpty()){
      this.primeiro = item;
      this.ultimo = item;
      this.primeiro.anterior=null;
      length++;
      return true;
    }
    if( posicao==length ){ // adicionar ao fim
      item.anterior = this.ultimo;
      this.ultimo.proximo = item;
      this.ultimo=item;
      item.proximo=null;
      length++;
      return true;
    }
    if(posicao!=0){
      Node ant = this.primeiro;
      int c=1;
      while(ant.proximo!=null){
        if(c==posicao){
          break;
        }
        c++;
        ant=ant.proximo;
      }
      item.proximo=ant.proximo; //obs
      item.anterior=ant; //obs
      ant.proximo.anterior = item; //obs
      ant.proximo=item; //obs
      length++;
      return true;
    }
    if(posicao==0){
      item.anterior=null;
      item.proximo=primeiro;
      primeiro.anterior=item;
      this.primeiro=item;
      return true;
    }
    return false;
}

  public boolean add(Node item) {
    return add(item, length);
  }

  public Node menor() {  
    Node menor = this.primeiro;
    Node temp = this.primeiro.proximo;
    while(temp!=null){
      if(menor.valor>temp.valor){
        menor=temp;
      }
      temp = temp.proximo;
    }
    return menor;
  }

  public Node maior() {  
    Node maior = this.primeiro;
    Node temp = this.primeiro.proximo;
    while(temp!=null){
      if(maior.valor<temp.valor){
        maior=temp;
      }
      temp = temp.proximo;
    }
    return maior;
  }

  public void print( boolean reverso ){
    Node aux = reverso ? this.ultimo : this.primeiro;
    String str = "[ ";
    if(aux==null){
      str+="]";
    }
    while(aux!=null){
      str += aux.valor;
      Node op = reverso ? aux.anterior : aux.proximo;
      str += (op==null)?" ]":", ";
      aux = op;
    }
    System.out.println(str);
  }

  public void print(){
    print(false);
  }
  
  @Override
  public String toString() {
    Node temp = this.primeiro;
    String str = "[ ";
    if(primeiro==null){
      return str+="]";
    }
    while(temp!=null){
      str += temp.valor;
      str += (temp.proximo==null)?" ]":", ";
      temp = temp.proximo;
    }
    return str;
  }
    
  public void sort() {
    // this.print();
    Node pivo = this.primeiro;
    while (pivo != null) {
        Node prox = pivo.proximo;
        while (prox != null) {
            if (pivo.valor > prox.valor) {
                Node temp = new Node( pivo.valor );
                pivo.valor = prox.valor;
                prox.valor = temp.valor;
            }
            // this.print();
            prox = prox.proximo;
        }
        pivo = pivo.proximo;
    }
  }

  
  public Node getNode(int index) {
    if(index<0 || index>=this.length){
      return null;
    }
    int cont=0;
    Node element=this.primeiro;
    while(cont!=index){
      element = element.proximo;
      cont++;
    }
    return element;
  }
 
  public Node buscar(int valor) {
    Node temp = this.primeiro;
    while(temp!=null){
      if(temp.valor==valor) return temp;
      temp=temp.proximo;
    }
    return new Node();
  }

  public boolean concatenar(Lista lista) {
    
    if(this.isEmpty() || lista.isEmpty()){
      return false;
    }
    lista.primeiro.anterior = this.ultimo;
    this.ultimo.proximo = lista.primeiro;
    this.ultimo = lista.ultimo;
    this.length += lista.length;
    return true;
  }

}
