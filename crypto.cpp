#include<iostream>
#include<string>
#include<cstdio>
#include<cstdlib>
#include<cmath>
#include<vector>

using namespace std;

string Alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";//only capital letters required
string Texte_depart="";//name required
string La_cle="";//name required
string Texte_crypte="";
string Adresse="";

//class
class Tab
{
    public : string nom;
    public : string texte;
    public : vector<int> tab;
    public : vector<int> tabRang;

    Tab(string a, string b)
    {

        nom = a;
        texte = b;
        int i,j,flag=0,Taille;
        //test if the string is empty
        if(texte[0]=='\0')
        {
            do{
                cout <<"Saisir "<<nom<<" :"<<endl;
                cin >>  texte;
                for(i=0;i<texte.size();i++){
                    //if out of the alphabet (no only capital letter in this contexte) -> red flag
                    if(texte[i]>=Alphabet[0] && texte[i]<=Alphabet[Alphabet.size()-1]){//-1 to avoid '\0'
                        flag =0;
                    }
                    else{
                        flag=1;
                        cout <<"erreur : phrase en majuscules sans c"<<char(130)<<"dille, accent ou tr"<<char(130)<<"ma requise"<<endl;
                        break;
                    }
                }
            }while(flag==1);
        }
        Taille = texte.length();
        //resize the tab to the length of the string
        tab.resize(Taille);
        tabRang.resize(Taille);
        //build of the tabs
        for(i=0;i<Taille;i++){
            tab[i]=int(texte[i]);
            j=-1;//starts at -1 so 'A' isn't skiped
            do
                j++;
            while (tab[i]!=int(Alphabet[j]));//Find the good rank ex A->1, E->5 etc
            tabRang[i]=j+1;//+1 to shift A->Z : 0->25 to 1->26
        }
        cout<<"tableau "<<nom<<" cr"<<char(130)<<char(130)<<endl;
    }

};

//Functions
void AfficheEtSauve(string avantTab, vector<int> tab,int sautLigne){
    int i;
    FILE* fichier = NULL;
    fichier = fopen(Adresse.c_str(),"a");//fopen is from c and work only with (const char*) -> conversion needed
    if (fichier != NULL)
    {
        //header on the beginig
        cout<<avantTab<<endl;
        fprintf(fichier,"%s\t",avantTab.c_str());//same here -> conversion needed
        //adding table after header
        for(i=0;i<tab.size();i++)
        {
            cout << tab[i]<<"\t";
            fprintf(fichier,"%d\t", tab[i]);
        }
        fprintf(fichier,"\n");
        cout<<endl;
        //adding new line in the file
        for(i=0;i<sautLigne;i++)
            fprintf(fichier,"\n");
        fclose (fichier);
    }
    else
        cout<<"Ouverture impossible"<<endl;
}

void AfficheEtSauve(string avantTab, string texte, int sautLigne){
    int i;
    FILE* fichier = NULL;
    fichier = fopen(Adresse.c_str(),"a");//fopen is from c and work only with (const char*) -> conversion needed
    if (fichier != NULL)
    {
        //header on the bedining
        cout<<avantTab<<endl;
        fprintf(fichier,"%s\t",avantTab.c_str());//same here -> conversion needed
        //adding table after header
        for(i=0;i<texte.size();i++)
        {
            cout << texte[i]<<"\t";
            fprintf(fichier,"%c\t", texte.c_str()[i]);//same here -> conversion needed
        }
        fprintf(fichier,"\n");
        cout<<endl;
        //adding new line in the file
        for(i=0;i<sautLigne;i++)
            fprintf(fichier,"\n");
        fclose (fichier);
    }
    else
        cout<<"Ouverture impossible"<<endl;
}

vector<int> Decalage(vector<int> Tcle,vector<int> Ttexte,int signe,int MAX)// name required for the shift function
{
    int i,j,Taille=Ttexte.size();
    vector<int> Tdecale(Taille);//shift table initiate from size of Text to crypt
    for(i=0,j=0;i<Taille;i++,j++)
    {
        (j==Tcle.size())?j=0:j=j;//if end of Tcle is reached -> back to first
        Tdecale[i]=Ttexte[i]+ signe*Tcle[j];
        if((Tdecale[i])>MAX)//if MAX of the alphabet size is reached -> do -MAX
            Tdecale[i]-=MAX;
    }
    return Tdecale;
}

string Cryptage(vector<int> aCrypter,vector<int> RangsLettres)// name required for the crypting function
{
    int i,j;
    string Crypte;
    Crypte.resize(aCrypter.size());
    for(i=0;i<aCrypter.size();i++)
    {
            j=-1;
            do
                j++;
            while (aCrypter[i]!=int(RangsLettres[j]));
            Crypte[i]=Alphabet[j];
    }
    return Crypte;
}

void Calcule_cle(string texteDep,string texteCrypte){// name required for the key finder function

    int i,index;
    string cleTrouvee,cleEchantillon,repetition,cleFinale;

    cout<<"****** Calcule de la cl"<<char(130)<<" de cryptage *******"<<endl;
    //recreate tables from string input
    Tab AZ("Alphabet",Alphabet);
    Tab Dep("Texte de depart",texteDep);
    Tab Crypte("Texte crypte",texteCrypte);

    cout<<Dep.nom<<endl<<Dep.texte<<endl;
    cout<<Crypte.nom<<endl<<Crypte.texte<<endl;

    vector<int> cleCalc(Dep.tab.size(),0);//new tab for the calc
    for(i=0;i<cleCalc.size();i++)
    {
        if(Dep.tabRang[i]>Crypte.tabRang[i])//if text rank > crypted rank
            cleCalc[i]=Crypte.tabRang[i]-Dep.tabRang[i]+AZ.tab.size()+1;//add 26
        else
            cleCalc[i]=Crypte.tabRang[i]-Dep.tabRang[i]+1;
    }
    cleTrouvee = Cryptage(cleCalc,AZ.tabRang); //key result in string
    cout<<"cl"<<char(130)<<" interm"<<char(130)<<"diaire trouv"<<char(130)<<"e : "<<endl<<cleTrouvee<<endl;

    //looking for repetition inside the key calculated

    for(i=1;i<cleTrouvee.length();i++){
        //make sample from small to big : C->CL->CLE->CLED etc
        cleEchantillon = cleTrouvee.substr(0,i);
        repetition = "";

        while(repetition.length() < cleTrouvee.length())
            repetition+=cleEchantillon;//fill repetition : CCC...C -> CLCLCL...CL -> etc

        if(cleTrouvee == repetition.substr(0,cleTrouvee.length())){//repetion can be longer than cleTrouvee
            cleFinale = cleEchantillon;//stop to the sample needed
            break;
        }
    }
    cout<<"variable repetition : "<<endl<<repetition<<endl;

    if(cleFinale=="")
        cout<<"La cl"<<char(130)<<" trouv"<<char(130)<<"e est possiblement incomplete"<<endl<<cleTrouvee<<endl;// full key here because no repetition found
    else{
        cout<<"Cl"<<char(130)<<" trouv"<<char(130)<<"e"<<endl<<cleFinale<<endl;
    }
}

int main()
{
    cout <<"Saisir l'emplacement du fichier Sortie.txt :"<<endl;
    cin >> Adresse;

    //**************  LETTER INITIATLISATION  *******************
    Tab AZ("Lettre",Alphabet);
    AfficheEtSauve(AZ.nom,Alphabet,0);
    AfficheEtSauve("Rang dans l'alphabet",AZ.tabRang,3);

    //**************  CRYPT KEY INITIATLISATION  *******************
    Tab cleCryptage("Cle de cryptage",La_cle);
    La_cle = cleCryptage.texte;//Update the global variable La_cle
    AfficheEtSauve(cleCryptage.nom,La_cle,0);
    vector<int> cleDecalee(cleCryptage.tab.size(),AZ.tabRang[0]);//Create table full of 1 (or 'A')
    cleDecalee = Decalage(cleDecalee,cleCryptage.tabRang,-1,0);// shift with - operator, no max
    AfficheEtSauve("Decalage par raport a la lettre A",cleDecalee,2);


    //**************  TEXT TO CRYPT INITIATLISATION  *******************
    Tab texteDep("Texte a crypter",Texte_depart);
    Texte_depart = texteDep.texte;//Update the global variable Texte_depart
    AfficheEtSauve(texteDep.nom,Texte_depart,0);
    AfficheEtSauve("Rang depart",texteDep.tabRang,0);


    //**************   MAKE THE KEY REPEATED WITH THE SAME SIZE OF TEXT  *******************
    vector<int> cleDecaleeTaille(texteDep.tab.size(),0);//create table with good size
    cleDecaleeTaille = Decalage(cleDecalee,cleDecaleeTaille,1,0);
    AfficheEtSauve("Decalage",cleDecaleeTaille,0);

    //**************   ADDITION BETWIN THE KEY AND THE TEXT TO CRYPT  *******************
    AfficheEtSauve("Rang apres decalage(somme)",Decalage(cleDecalee,texteDep.tabRang,1,0),0);


    //**************   SUBSTRACTION WITH THE MAX LIMIT  *******************
    vector<int> texteDecale(texteDep.tab.size(),0);//table initialisation
    //with max = alphabet size
    texteDecale = Decalage(cleDecalee,texteDep.tabRang,1,Alphabet.size());
    AfficheEtSauve("Rang Final (Total ou Total-26)",texteDecale,0);


    //**************   CRYPTING  *******************
    Texte_crypte = Cryptage(texteDecale,AZ.tabRang);//Update the global variable Texte_crypte
    AfficheEtSauve("texte crypte (lettre associe au rang final)",Texte_crypte,1);

    AfficheEtSauve("Texte de depart","",0);
    AfficheEtSauve(Texte_depart,"",1);


    AfficheEtSauve("Texte crypte","",0);
    AfficheEtSauve(Texte_crypte,"",1);

    //**************   KEY FINDER  *******************
    Calcule_cle(Texte_depart,Texte_crypte);

    return 0;
}

