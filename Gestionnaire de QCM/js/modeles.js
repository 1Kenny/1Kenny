/* globals renderQuizzes renderUserBtn */

// //////////////////////////////////////////////////////////////////////////////
// LE MODELE, a.k.a, ETAT GLOBAL
// //////////////////////////////////////////////////////////////////////////////

// un objet global pour encapsuler l'état de l'application
// on pourrait le stocker dans le LocalStorage par exemple
const state = {
  // la clef de l'utilisateur exemple la mienne : 926d09aa-d525-4963-8a8a-128971132a7b

  xApiKey: '51672e8e-0563-4cf3-b2a7-6378c50f8475',

  // l'URL du serveur où accéder aux données
  serverUrl: 'https://lifap5.univ-lyon1.fr',

  // la liste des questios
  quizzes: [],

  //la liste de tes questionaires
  MyQuizzes: [],

  //la liste des questiaores réponduz.
  Answers: [],

  // le questionnaire momentanement choisi
  currentQID: undefined,

  currentQuizz: undefined,

  currentQuestions: undefined,

  headers(){
    const headers = new Headers();
    headers.set('X-API-KEY', this.xApiKey);
    headers.set('Accept', 'application/json');
    headers.set('Content-Type', 'application/json');
    return headers;
  }
};

// une méthode pour l'objet 'state' qui va générer les headers pour les appel à fetch

// //////////////////////////////////////////////////////////////////////////////
// OUTILS génériques
// //////////////////////////////////////////////////////////////////////////////

// un filtre simple pour récupérer les réponses HTTP qui correspondent à des
// erreurs client (4xx) ou serveur (5xx)
// eslint-disable-next-line no-unused-vars
function filterHttpResponse(response) {
  return response
    .json()
    .then((data) => {
      if (response.status >= 400 && response.status < 600) {
        throw new Error(`${data.name}: ${data.message}`);
      }
      return data;
    })
    .catch((err) => console.error(`Error on json: ${err}`));
}

//load une donnees a l'url url, puis appel le callback (renvoie juste l'objet json si aucun callback n'est donné)
const load = (url,callback = (data)=>data) => {
  return fetch(url, {method: 'GET',headers: state.headers()})
    .then(filterHttpResponse)
    .then(callback);
}

const repondre = (id,question_id,proposition_id) =>{

  const url = `quizzes/${id}/questions/${question_id}/answers/${proposition_id}`;
  return fetch(`${state.serverUrl}/${url}`, {method: 'POST',headers: state.headers()})
    .then(filterHttpResponse);
}

const addQuizz = (title,description)=>
  fetch(`${state.serverUrl}/quizzes/`, 
    {method: 'POST',
    body:`{"title":"${titre}","description":"${description}"}`,
    headers: state.headers()})
    .then(filterHttpResponse);



const addQuestion = (id,question)=>
  fetch(`${state.serverUrl}/quizzes/${id}/questions`,
    {method: 'POST',
    body: question,
    headers: state.headers()})
    .then(filterHttpResponse);

const put = (etat,id)=>
{
  const url = `${state.serverUrl}/quizzes/${id}`;
  return load(url,data=>
    fetch(url,
      {method: 'PUT',
      body: `{"title":"${data.title}","description":"${data.description}","open":${etat}}`,
      headers: state.headers()})
    .then(filterHttpResponse)
    .then(data=>state.currentQuizz = data)
    .then(load(`${url}/questions`,(data)=>renderMyQuizz(data))))
};


const close = id=>
  put('false',id);
;

const open = id=>
  put('true',id);
;
// //////////////////////////////////////////////////////////////////////////////
// DONNEES DES UTILISATEURS
// //////////////////////////////////////////////////////////////////////////////

// mise-à-jour asynchrone de l'état avec les informations de l'utilisateur
// l'utilisateur est identifié via sa clef X-API-KEY lue dans l'état
// eslint-disable-next-line no-unused-vars
const getUser = () => {
    console.debug(`@getUser()`);
    const url = `${state.serverUrl}/users/whoami`;
    return load(url,(data) => {
        // /!\ ICI L'ETAT EST MODIFIE /!\
        state.user = data;
        // on lance le rendu du bouton de login
      })
      .then(renderUserBtn())
};

// //////////////////////////////////////////////////////////////////////////////
// DONNEES DES QUIZZES
// //////////////////////////////////////////////////////////////////////////////

// mise-à-jour asynchrone de l'état avec les informations de l'utilisateur
// getQuizzes téléload la page 'p' des quizzes et la met dans l'état
// puis relance le rendu
// eslint-disable-next-line no-unused-vars


const getMyQuizzes = () => {

  title = document.getElementById('id-my-quizzes-title');
  console.debug(`@getMyQuizzes()`);
  if(state.xApiKey!=''){
    title.innerHTML="";
    const url = `${state.serverUrl}/users/quizzes`;
    return load(url,(data)=>{
      state.MyQuizzes = data;
      renderMyQuizzes(data);
    })
  }
  else{
    list = document.getElementById('id-my-quizzes-list');
    main = document.getElementById('id-my-quizzes-main');
    list.innerHTML="";
    main.innerHTML="";
    title.innerHTML=`connectez vous pour acceder à ce contenue`;
  }
}



function getReponses () {
    const url = `${state.serverUrl}/users/answers`;
    setHeader();
  fetch(url, { method: 'GET', headers: state.headers })
    .then(filterHttpResponse)
    .then( (data) => {
      state.answers = data;
      renderAnswers();
      if (state.currentAnswer)
        renderCurrentAnswer();        
    } );
  
}





const getMyQuizz = (id) => {
  console.debug(`@getMyQuizz(${id})`);
  const url = `${state.serverUrl}/quizzes/${id}`;
  load(url,data=>state.currentQuizz=data)
  .then(load(`${url}/questions`,(data)=>{renderMyQuizz(data);renderMyQuizzes();}));
}

const getAnswers = () => {
  title = document.getElementById('id-my-answers-title');
  console.debug(`@getAnswers()`);
  if(state.xApiKey!=''){
    title.innerHTML="";
    const url = `${state.serverUrl}/users/answers`;
    load(url,data=>
                  {
                    state.Answers = data;
                    renderAnswers();
                  }
          );
  }
  else{
    list = document.getElementById('id-answers-list');
    main = document.getElementById('id-answer-main');
    list.innerHTML="";
    main.innerHTML="";
    title.innerHTML=`connectez vous pour acceder à ce contenue`;
  }
    
}

const getAnswer = (id) => {
  console.debug(`@getAnswer(${id})`);
  const url = `${state.serverUrl}/quizzes/${state.Answers[id].quiz_id}`;
  load(url,data=>state.currentQuizz=data)
    .then(load(`${url}/questions`,data=>{renderAnswer(data,state.Answers[id].answers)}))
    .then(renderAnswers());
}

const getQuizzes = (p = 1) => {
  console.debug(`@getQuizzes(${p})`);
  const url = `${state.serverUrl}/quizzes/?page=${p}`;

  // le téléloadment est asynchrone, là màj de l'état et le rendu se fait dans le '.then'
  return load(url,(data)=>{state.quizzes = data; return renderQuizzes();});
};




function getQuizzess (p = 1, r = false)  {
  getReponses();
  if (!state.user)
    r = false;
  if (r) {
    state.nbResultats = 0;
    document.getElementById('id-all-quizzes-main').innerHTML = '<h4> Patientez la Recherche est en cours <h4> ';
  }

  let url = '';
  if (testOnlyMy())
    url = `${state.serverUrl}/users/quizzes/?page=${p}`;
  else
    url = `${state.serverUrl}/quizzes/?page=${p}`;

  fetch(url, { method: 'GET', headers: state.headers })
      .then(filterHttpResponse)
      .then( (data) => {
        state.quizzes = data;
        renderQuizzes();
        if (r) {
          document.getElementById('id-all-quizzes-main').innerHTML = '<h4> VOTRE Recherche donne'  +  ':' + state.nbResultats + ' ' + ' résultat(s).<h4>';
        }
      }
    );
}









function estResultat (quiz) {
  const r = document.getElementById('search').value;
  if (r === '')
    return false;
  if ( (quiz.owner_id.indexOf(r) != -1) || (quiz.description.indexOf(r) != -1) || (quiz.title.indexOf(r) != -1) || (quiz.created_at.indexOf(r) != -1) ) 
    return true;
    const questions = JSON.parse( getJson('https://lifap5.univ-lyon1.fr/quizzes/' + quiz.quiz_id + '/questions') );
  for(var q = 0; (q < questions.length); q++ ) {
        if (questions[q].sentence.indexOf(r) != -1) 
      return true;
        for(var p = 0; (p < questions[q].propositions_number); p++) {
            if (questions[q].propositions[p].content.indexOf(r) != -1) 
        return true;
        } 
    }
  return false;
}


const getQuizz = (id) => {//met le questionnaire d'id id dans l'etat actuel
  const url = `${state.serverUrl}/quizzes/${id}`;
  load(url,(data)=>{state.currentQuizz=data;})
  .then(load(`${url}/questions`,data=>{state.currentQuestions=data;})

    .then(()=>{
      renderQuizzes();
      renderCurrentQuizz();}) 
  );
}



function tabCheckedSiDejaFait (qid) {
  const tab = [];
  const answer = state.answers.find(q => q.quiz_id === qid);
  if (answer) {
    for( var a = 0; (a < answer.answers.length); a++ )
      tab[answer.answers[a].question_id] = answer.answers[a].proposition_id;
  }
  return tab;
}



