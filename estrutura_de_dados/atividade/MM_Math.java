package atividade;

public class MM_Math {

  public static String multiplicar2(String str1, String str2) {
    ListaDupla lista1 = fatiar(str1), lista2 = fatiar(str2), resultado = new ListaDupla();
    Node calda1 = lista1.fim, calda2 = lista2.fim;
    int buffer=0, n1=0, n2=0, res=0;
    Node aux = null;
    Node aux2 = null;
       
    while (calda1!=null) {
      calda2 = lista2.fim;
      n1 = toInt(calda1.value);
      aux2 = aux;
      while (calda2!=null) {
        n2 = toInt(calda2.value);
        res = n1*n2;
        if(aux2!=null){
          res += toInt(aux2.value);
          buffer = res/100 ;
          res -= buffer*100;
          aux2.value = ""+res;
          if(aux2.anterior==null){
            if(calda2.anterior!=null){
              resultado.addStart(buffer);
            }else{
              if (buffer!=0) resultado.addStart(buffer);
            }
          }else{
            aux2.anterior.value = ""+(buffer+toInt(aux2.anterior.value));
          }
        }else if( resultado.inicio!=null ){
          res += toInt(resultado.inicio.value);
          buffer = res/100;
          res -= buffer*100;
          resultado.inicio.value = ""+res;
          if(calda2.anterior!=null) {
            resultado.addStart(buffer);
          }else if(buffer!=0){
            resultado.addStart(buffer);
          }
        }else{
          buffer = res/100;
          res -= buffer*100;
          resultado.addStart(res);
          resultado.addStart(buffer);
        }
        if(aux2!=null) aux2 = aux2.anterior;
        calda2 = calda2.anterior;
      }
      aux = ( aux==null  ) ? resultado.fim.anterior : aux.anterior;
      // System.out.println();
      calda1=calda1.anterior;
    }
    System.out.println();
    return resultado.toString();
    // return resultado.str();
  }

  public static int toInt(String str) {
    return Integer.parseInt(str);
  }
  
  
  public static String multiplicar(String str1, String str2) {
    ListaDupla lista1 = fatiar(str1), lista2 = fatiar(str2);
    Node calda1 = lista1.fim, calda2 = lista2.fim;
    ListaDupla resultado = gerarListaDupla(calda1, calda2);
    int buffer=0, n1=0, n2=0, res=0;
    Node aux = resultado.fim;

    while (calda1!=null) {
      calda2 = lista2.fim;
      n1 = Integer.parseInt(calda1.value);
      Node aux2 = aux;
      while (calda2!=null) {
        n2 = Integer.parseInt(calda2.value);
        buffer = Integer.parseInt(aux2.value);
        res = n1*n2+buffer;
        buffer = res/100;
        res -= buffer*100;
        aux2.value = (res<9)? "0"+res : ""+res ;
        buffer += Integer.parseInt(aux2.anterior.value);
        aux2.anterior.value = (buffer<9) ? "0"+buffer: ""+buffer;
        aux2 = aux2.anterior;
        calda2 = calda2.anterior;
      }
      aux=aux.anterior;
      calda1=calda1.anterior;
    }
    if(nulo(resultado.inicio.value)) resultado.deleteStart();
    resultado.inicio.value = ""+Integer.parseInt(resultado.inicio.value);
    return resultado.str();
    // return resultado.toString(); //
  }

  private static ListaDupla gerarListaDupla(Node calda1, Node calda2) {
    ListaDupla lista = new ListaDupla();
    while (calda1!=null) {
      lista.addStart("0");
      while (calda2!=null) {
        lista.addStart("0");
        calda2 = calda2.anterior;
      }
      calda1 = calda1.anterior;
    }
    return lista;
  }

  private static boolean nulo(String num) {
    Integer n = Integer.parseInt(num);
    return n==0?true:false;
  }
  
  
  private static ListaDupla fatiar(String str) {
    ListaDupla lista = new ListaDupla();
    int tam = str.length();
    String num="";
    int inicio = 0;
    if( tam%2==1 ){
      num += str.charAt(inicio++);
      lista.addEnd(num);
      num="";
    }
    for (int i = inicio; i < tam; i++) {
      char c1 = 0, c2 = 0; 
      try{
        c1 = str.charAt(i);
      }catch(Exception e){
        break;
      }
      try{
        c2 = str.charAt(++i);
      }catch(Exception e){
        lista.addEnd(c1);
        break;
      }
      lista.addEnd(c1+""+c2);
    }
    return lista;
  }
}
