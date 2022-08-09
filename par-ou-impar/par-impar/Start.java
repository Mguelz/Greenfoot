import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Start extends World
{
    /**
     * Ao clicar em uma tecla, voce escolhe sua opcao, impar ou par
     * o sistema gerara um numero entre 1 e 2
     * 
     * exemplo
     * ao escolher impar, por exemplo, se o sistema gerar 1 voce venceu,
     * se o sistema gerar 2 voce perdeu.
     */
    public Start()
    {    
        super(1200, 600, 1); 

        
        showText("Clique 'i' para escolher impar", 200, 280);
        addObject(new Impar(), 195, 315);
        
        showText("Clique 'p' para escolher par", 780, 270);
        addObject(new Par(), 780, 305);
        
        //
        addObject(new ImparMelhor(), 1200, 600);
        addObject(new ParMelhor(), 1200, 600);

    }
}
