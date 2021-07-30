/* global state getQuizzes */

// //////////////////////////////////////////////////////////////////////////////
// HTML : fonctions génération de HTML à partir des données passées en paramètre
// //////////////////////////////////////////////////////////////////////////////

// génération d'une liste de quizzes avec deux boutons en bas
const htmlQuizzesList = (quizzes, curr, total) => {
  console.debug(`@htmlQuizzesList(.., ${curr}, ${total})`);

  // un élement <li></li> pour chaque quizz. Noter qu'on fixe une donnée
  // data-quizzid qui sera accessible en JS via element.dataset.quizzid.
  // On définit aussi .modal-trigger et data-target="id-modal-quizz-menu"
  // pour qu'une fenêtre modale soit affichée quand on clique dessus
  // VOIR https://materializecss.com/modals.html
 

  const quizzesLIst = quizzes.map(//afficher liste des questionnaires
    (q) =>
      `<li class="collection-item moddal-trigger cyan `+(q.quiz_id==state.currentQID? "" : "lighten-5")+`" style="cursor: pointer;" data-target="id-modal-quizz-menu" data-quizzid="${q.quiz_id}">
        <h5>
            ${q.title}
        </h5>
           ${q.description} <a class="chip">${q.owner_id}</a>
      </li>`
  );

  // le bouton "<" pour revenir à la page précédente, ou rien si c'est la première page
  // on fixe une donnée data-page pour savoir où aller via JS via element.dataset.page
  const prevBtn =
    curr !== 1
      ? `<button id="id-prev-quizzes" data-page="${curr -
          1}" class="btn btn-dark"><i class="material-icons">navigate_before</i></button>`
      : '';
// Boutons 2 3 4 et 5
       const deux =
    curr === 1
      ? `<button id="id-deux-quizzes" data-page="${curr + 1
          }" class="btn btn-dark">2</button>`
      : '';

       const trois =
    curr === 2
      ? `<button id="id-trois-quizzes" data-page="${curr + 1
          }" class="btn btn-dark">3</button>`
      : '';


       const quatre =
    curr === 3
      ? `<button id="id-quatre-quizzes" data-page="${curr +
          1}" class="btn btn-dark">4</button>`
      : '';


       const cinq =
    curr === 4
      ? `<button id="id-cinq-quizzes" data-page="${curr +
          1}" class="btn btn-dark">5</button>`
      : '';


    
  // le bouton ">" pour aller à la page suivante, ou rien si c'est la première page
  const nextBtn =
    curr !== 6
      ? `<button id="id-next-quizzes" data-page="${curr +
          1}" class="btn btn-dark"><i class="material-icons">navigate_next</i></button>`
      : '';

  // La liste complète et des boutons en bas
  const html = `
  <ul class="collection">
    ${quizzesLIst.join('')}
  </ul>



  <div class="row">      
    <div class="col l4 left-align">${prevBtn}</div>
    <div class="col l4 ">${deux}</div>
    <div class="col l4 ">${trois}</div>
    <div class="col l4 ">${quatre}</div>
    <div class="col l4 ">${cinq}</div>
    <div class="col l4 right-align">${nextBtn}</div>
  </div>
  `;
  return html;
};

const QuizzToHTML = (questions) => { // prend en compte le clic sur tous les questionaires
  return `<form id="form-quiz" action="#" method="get" class="reponse">${questions.map(n=>`<span style="font-size:2em ; background-color:green;">${n.question_id+1} : ${n.sentence}</span><br/>
    <div class="proposition${n.question_id}">
      ${n.propositions.map(p=>`<label><input type="radio" id="${p.proposition_id}" name="${n.question_id}" value="${p.proposition_id}"/><span style="color: grey;">${p.content}</span></label>`).join('<br/>\n')}
    </div>`).join('<br/>\n')}`

    +(state.user? (state.currentQuizz.open? '<button class="btn btn-primary btn-lg active" type="submit" id="submitAnswer" >Soumettre<i class="fa fa-rocket" style="font-size:24px"></i></button>' : '<span style= "border:3px solid black; border-radius:10px;font-style: italic;font-weight: bold;" >fermer questionaire</span>'):'<span style= "border:1px solid black; border-radius:5px;" >Etablir une Connexion pour répondre </span>')+
    `</form>`;
};


// //////////////////////////////////////////////////////////////////////////////
// RENDUS : mise en place du HTML dans le DOM et association des événemets
// //////////////////////////////////////////////////////////////////////////////

// met la liste HTML dans le DOM et associe les handlers aux événements
// eslint-disable-next-line no-unused-vars
function renderQuizzes() {
  console.debug(`@renderQuizzes()`);

  // les éléments à mettre à jour : le conteneur pour la liste des quizz
  const usersElt = document.getElementById('id-all-quizzes-list');
  // une fenêtre modale définie dans le HTML
  const modal = document.getElementById('id-modal-quizz-menu');

  // on appelle la fonction de généraion et on met le HTML produit dans le DOM
  usersElt.innerHTML = htmlQuizzesList(
    state.quizzes.results,
    state.quizzes.currentPage,
    state.quizzes.nbPages
  );

  // /!\ il faut que l'affectation usersElt.innerHTML = ... ait eu lieu pour
  // /!\ que prevBtn, nextBtn et quizzes en soient pas null
  // les éléments à mettre à jour : les boutons
  const prevBtn = document.getElementById('id-prev-quizzes');
  const deuxBtn = document.getElementById('id-deux-quizzes');
  const troisBtn = document.getElementById('id-trois-quizzes');
  const quatreBtn = document.getElementById('id-quatre-quizzes');
  const cinqBtn = document.getElementById('id-cinq-quizzes');
  const nextBtn = document.getElementById('id-next-quizzes');
  // la liste de tous les quizzes individuels
  const quizzes = document.querySelectorAll('#id-all-quizzes-list li');

  // les handlers quand on clique sur "<" ou ">"
  function clickBtnPager() {
    // remet à jour les données de state en demandant la page
    // identifiée dans l'attribut data-page
    // noter ici le 'this' QUI FAIT AUTOMATIQUEMENT REFERENCE
    // A L'ELEMENT AUQUEL ON ATTACHE CE HANDLER
    getQuizzes(this.dataset.page);
  }
  if (prevBtn) prevBtn.onclick = clickBtnPager;
  if (deuxBtn)deuxBtn.onclick = clickBtnPager;
  if (troisBtn)troisBtn.onclick = clickBtnPager; 
  if (quatreBtn)quatreBtn.onclick = clickBtnPager;  
  if (cinqBtn)cinqBtn.onclick = clickBtnPager; 
  if (nextBtn) nextBtn.onclick = clickBtnPager;

  // qd on clique sur un quizz, on change sont contenu avant affichage
  // l'affichage sera automatiquement déclenché par materializecss car on
  // a définit .modal-trigger et data-target="id-modal-quizz-menu" dans le HTML
  function clickQuiz() {
    const quizzId = this.dataset.quizzid;
    console.debug(`@clickQuiz(${quizzId})`);
    const addr = `${state.serverUrl}/quizzes/${quizzId}`;
    const main = document.getElementById('id-all-quizzes-main');
    main.innerHTML=`<img src="https://thumbs.gfycat.com/KnobbyWeirdIlladopsis.webp"/>`
    /*const html = `
      <p>Vous pourriez aller voir <a href="${addr}">${addr}</a>
      ou <a href="${addr}/questions">${addr}/questions</a> pour ses questions<p>.`;
    //modal.children[0].innerHTML = html;*/
    state.currentQID = quizzId;
    getQuizz(quizzId);
    // eslint-disable-next-line no-use-before-define
  }

  // pour chaque quizz, on lui associe son handler
  quizzes.forEach((q) => {
    q.onclick = clickQuiz;
  });
}

function HTMLquizzList(Quizzes,ID){  // gestion de mes questionnaires et reponses
  return `<ul class="collection" id="list_${ID}">

            ${Quizzes.reduce((acc,q)=> 

                `${acc}
                <li id="${ID}_${q.quiz_id}" class="collection-item moddal-trigger cyan `+(q.quiz_id==state.currentQID? "" : "lighten-5")+`" style="cursor: pointer;" >
                    <h5>${q.title}</h5>
                    ${q.description} <a class="chip">${q.owner_id}</a>
                </li>`

            ,"")}

          </ul>`;

}










function renderCreate(){ //creer un questionnaire
  state.currentQID=undefined;
  renderMyQuizzes();
  const bouton = document.getElementById('create').classList;
  bouton.remove("blue");
  bouton.add("green");
  const main = document.getElementById("id-my-quizzes-main");


  main.innerHTML=`
  <form id="form_create" method="get">
    <input type="text" placeholder="Titre" name="titre" id="titre_ques"/>
    <textarea placeholder="Décrivez votre questionnaire. " name="description" id="decri_ques"></textarea>
    <button class="btn waves-effect waves-light left" type="submit">Ajouter</button>
  </form>`;

  const form = document.getElementById('form_create');
  form.onsubmit = function(ev){ ev.preventDefault();
                                formData = new FormData(this);
                                let tab = [];
                                for(const pair of formData.entries()){
                                  tab.push(pair);
                                }
                                addQuizz(tab[0][1],tab[1][1])
                                .then(getQuizzes())
                                .then(getMyQuizzes());
                                alert("Votre questionnaire a ete créé ");
                              }
}

function renderMyQuizzes(Quizzes=state.MyQuizzes){// partie pour ajouter les questionnaires

  const myquizz = document.getElementById('id-my-quizzes-list');
  myquizz.innerHTML=HTMLquizzList(Quizzes,"quizz");
  const list = document.getElementById("list_quizz");
  list.innerHTML=list.innerHTML+`<li id="create" class="collection-item moddal-trigger cyan" style="font-size:5em;text-align:center;cursor:pointer;color:black;"><i class='fas fa-edit' style='font-size:36px'></i></li>`;

  Quizzes.map(p=>
    document.getElementById(`quizz_${p.quiz_id}`).onclick=()=>{const main = document.getElementById('id-my-quizzes-main');
    main.innerHTML=`<img src="https://thumbs.gfycat.com/KnobbyWeirdIlladopsis.webp"/>`;state.currentQID=p.quiz_id;getMyQuizz(p.quiz_id);}
  );

  document.getElementById("create").onclick=()=>renderCreate();
}

function renderAnswers(Quizzes=state.Answers){

  const answers = document.getElementById('id-answers-list');
  answers.innerHTML= HTMLquizzList(Quizzes,"answer");
  Quizzes.map((p,n)=>
    document.getElementById(`answer_${p.quiz_id}`).onclick=()=>{const main = document.getElementById('id-answer-main');
    main.innerHTML=`<img src="https://thumbs.gfycat.com/KnobbyWeirdIlladopsis.webp"/>`;state.currentQID=p.quiz_id;getAnswer(n);}
  );
}


function renderAnswer(questions,answers){ //affiche les reponses soumise
  const main = document.getElementById('id-answer-main');
  main.innerHTML = questions.reduce((acc,n,i)=>`${acc}<span style="font-size:2em ; background-color:green;">${i+1} : ${n.sentence}</span><br/>
    <div class="proposition${n.question_id}">
      ${n.propositions.reduce((acc,p)=>`${acc}<span style="margin-left:1em;">${p.proposition_id+1} : ${p.content}</span><br/>\n`,"")}
    </div><br/>
    <div id="reponse${n.question_id}" style="font-size: 1.2em;">
      Cette question reste non repondue
    </div>`,"");
  answers.map(rep=>{
      const proposition = document.getElementById(`reponse${rep.question_id}`);
      proposition.innerHTML=`<span style="font-weight: bold; font-style: italic;">Votre Reponse est la suivante: ${questions[rep.question_id].propositions[rep.proposition_id].content}</span> </br>
       </br>
       <bouton onclick="modification()" class="btn btn-outline-success" id="search-btn" type="submit"> Pour modifier cliquez ici </bouton> </br>
       </br>`
      
    });
}

////modification du questionnaire

function modification(){ //affiche les reponses soumise
//lert("vous etes sur le point de modififier votre reponse, voulez vous cotinuer ?"); 
  const main = document.getElementById('id-answer-main');
  main.innerHTML = questions.reduce((acc,n,i)=>`${acc}<span style="font-size:2em ; background-color:yellow;">${i+1} : ${n.sentence}</span><br/>
    <div class="proposition${n.question_id}">
      ${n.propositions.reduce((acc,p)=>`${acc}<span style="margin-left:1em;">${p.proposition_id+1} : ${p.content}</span><br/>\n`,"")}
    </div><br/>
    <div id="reponse${n.question_id}" style="font-size: 1.2em;">
      Cette question reste non repondue
    </div>`,"");
  answers.map(rep=>{
      const proposition = document.getElementById(`reponse${rep.question_id}`);
      proposition.innerHTML=`<span style="font-weight: bold; font-style: italic;">Votre Reponse est la suivante: ${questions[rep.question_id].propositions[rep.proposition_id].content}</span> </br>`
      
    });
   
}

const MyQuizzToHTML = (questions) => {
  return `<form id="form-quiz" action="#" method="get" class="reponse">${questions.map(n=>`<span style="font-size:2em ; background-color:green;">${n.question_id+1} : ${n.sentence}</span><br/>
    <div class="proposition${n.question_id}">
      ${n.propositions.map(p=>`<label><input type="radio" id="${p.proposition_id}" name="${n.question_id}" value="${p.proposition_id}"/><span style="color: grey;">${p.content}</span></label>`).join('<br/>\n')}
    </div>`).join('<br/>\n')}`

    +(state.currentQuizz.open? '<button id="close_quizz" class="btn waves-effect waves-light chip" type="submit" id="submitAnswer">fermer le questionnaire</button>' : '<button id="open_quizz" class="btn waves-effect waves-light chip" type="submit" id="submitAnswer">ouvrir le questionnaire</button >')+ `</form>`;
};

function renderMyQuizz(questions){
  const id = state.currentQID;//pour ne pas que les futur changement de currentQID affect cette fonction si elle n'est pas appelée
  const qi = questions.reduce((acc,n)=> n['question_id'] ,"");
  const question_id = (qi===""? -1:qi);
  const bouton = document.getElementById('create').classList;
  bouton.remove("blue");
  bouton.add("green");
  const main = document.getElementById('id-my-quizzes-main');
  main.innerHTML = MyQuizzToHTML(questions)+`<br/><div id="id-create-main"><button id="add_question" class="btn waves-effect waves-light chip" >ajouter une question</button></div>`;
  if(state.currentQuizz.open){
    document.getElementById('close_quizz').onclick=()=>close(id);
  }
  else{
    document.getElementById('open_quizz').onclick=()=>open(id);
  } 
  document.getElementById('add_question').onclick=()=>{
            document.getElementById('id-create-main').innerHTML=
            `<br/><br/>
             <form id="ajout_question" method="get">
             <input type="text" name="intitule" placeholder="Theme du questionnaire" style="font-size:2.5em;" id="Theme_propo"/>
             <div id="propositions">
             <label>1ere proposition </label><input type="text" name="1ere" id="propo1"/>
             <label>2eme proposition </label><input type="text" name="2eme" id="propo2"/>
             </div>
             <label>Choisisez la bonne réponse :</label>
             <select name="correct_answer" size="1" id="correct" style="display:inline;">
             <option value="I">I</option>
             <option value="II">II</option>
             </select>
             <button type="button" id="add_proposition" class="btn waves-effect waves-light chip" >ajouter une proposition</button>
             <button type="submit" class="btn waves-effect waves-light chip">Valider </button>
             </form>`;
                      document.getElementById('add_proposition').onclick=()=>{const props = document.getElementById('propositions');
                       const nbProps = (props.childNodes.length-1)/2;
                       const input = document.createElement("INPUT");
                       input.type="text"; input.name = nbProps;
                       const label = document.createElement("LABEL");
                       label.appendChild(document.createTextNode(`proposition ${nbProps}`));
                        props.appendChild(label);
                        props.appendChild(input);
                        const options = document.getElementById('correct');
                        options.innerHTML+=`<option value="${nbProps}">${nbProps}</option>`}
                                 document.getElementById('ajout_question').onsubmit= function(ev){
                                  ev.preventDefault();
                                  formData = new FormData(this);
                                  var propositions = [];
                                  const data = {};
                                  for(var pair of formData.entries()){
                                  data[pair[0]]=pair[1];
                                  if(pair[0]!=="intitule"&&pair[0]!=="correct_answer"){
                                   propositions[pair[0]-1]=pair[1];
                                                            }
                                                          }
                                      console.debug(propositions);
                                         const object = 
                                    `{ "question_id" : ${question_id+1},\n"sentence" : "${data['intitule']}",\n"propositions" : \n\t[${propositions.map((n,i)=>`{ "content" : "${n}", "proposition_id" : ${i}, "correct" : ${(i==data['correct_answer']-1? "true":"false")} }`)
                                          .join("\n\t,")}]}`;
                                           console.debug(object);
                                           addQuestion(id,object)
                                                          .then(()=>{
                                                            main.innerHTML=`<img src="https://thumbs.gfycat.com/KnobbyWeirdIlladopsis.webp"/>`;
                                                            state.currentQID=id;
                                                            getMyQuizz(id);});
                                                          
                                                        }
                                                      }
}

function renderCurrentQuizz(questions=state.currentQuestions) {//met le quizz et les questions dans le main
  const main = document.getElementById('id-all-quizzes-main');
  main.innerHTML = QuizzToHTML(questions);

  const id = state.currentQID;

  const form = document.getElementById('form-quiz');

  form.onsubmit = function(ev) { ev.preventDefault();
                                 formData = new FormData(this);
                                 for(var pair of formData.entries()){
                                   repondre(id,pair[0],pair[1])
                                    .then(state.currentQID=undefined)
                                    .then(getAnswers);
                                 }
                                 alert(`Reponse soumise`); }
}

// quand on clique sur le bouton de login, il nous dit qui on est
// eslint-disable-next-line no-unused-vars
const renderUserBtn = () => {
  const btn = document.getElementById('id-login');
  btn.onclick = () => {
    if (state.user) {
      // eslint-disable-next-line no-alert
      const saisie=confirm(
        `Connexion etablie Bievenue a vous ${state.user.firstname} ${state.user.lastname}\n. Souhaitez vous vous déconnecter ?`//on peut pas se déloguer mais seulement changer d'utilisateur.
      );
      if(saisie){
        state.xApiKey='';
      }
    } else {
      // eslint-disable-next-line no-alert
      const saisie=prompt(
        `Pour commencer, saissez votre clef`
      );
      if(saisie!==null&saisie!==''){
        state.xApiKey=saisie;
      }
    }
    getUser()
    .then(getMyQuizzes())
    .then(getAnswers())
    .then(()=>{
        main=document.getElementById('id-all-quizzes-main');
        main.innerHTML = "";
        state.currentQID = undefined;
        renderQuizzes();
      });
  };
};


 var saisie = document.getElementById("recher").value;

function chercher(){
   if(saisie!==null&saisie!==''){
    alert(" VOUS AVEZ SAISI : " + " << " + saisie + " >> " + " " + " CLIQUEZ OK POUR VOIR LE RESULTAT ");
   }
  else {
     alert("REPRENEZ VOTRE SAISIE" );
   }
}


function supprimer () {
    document.getElementById('recher').value = '';
    document.getElementById('id-all-quizzes-main').innerHTML = '';
    getQuizzes();

    ///document.getElementById("diretct").innerHTML = Null ; a revoir avec le script inverse de la premiere boucle de renderHTlm. 
}
///AXkey:    51672e8e-0563-4cf3-b2a7-6378c50f8475

