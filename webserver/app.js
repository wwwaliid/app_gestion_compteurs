const express = require("express");
const app = express();
const bodyParser = require("body-parser");



app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

//postgresql
const { Client } = require('pg')
const client = new Client({
  user: 'postgres',
  host: 'localhost',
  database: 'amendis-app',
  password: '123456',
  port: 5432,
})
client.connect(function(err) {
  if (err) throw err;
  console.log("Connected!");
});


app.get('/compteurs', (request,response) => {
    //code to perform particular action.
    //To access GET variable use req.query() and req.params() methods.

    console.log("compteurs!!!!");
    client.query('SELECT * FROM compteurs ORDER BY id ASC', (error, results) => {
      if (error) {
        throw error
      }
      response.send(results.rows);
    })
});

app.post('/editindex', (request,response) => {
  console.log(request.body)

  client.query('UPDATE compteurs SET index = $1, ancien_index = $2, date_releve = $3 WHERE id = $4',[request.body.index, request.body.ancien_index, request.body.date_releve, request.body.id], (error, results) => {
    console.log('query sent');
    
  })

});

app.post('/signin', (request,response) => {
  console.log(request.body)

  client.query('SELECT * FROM users WHERE email=$1 AND password=$2',[request.body.email, request.body.password], (error, results) => {
    if (error) {
      throw "erroooooooor"
    }
    else{
      console.log(results.rows[0]);
      response.send(results.rows[0])
    }
  })
});

app.post('/signup', (request,response) => {
  console.log(request.body)

  client.query('INSERT INTO users(nom, email, password, role) VALUES ($1,$2,$3,$4)',[request.body.nom, request.body.email, request.body.password, request.body.role], (error, results) => {
    if (error) {
      throw "erroooooooor"
    }
    else{
    }
  })
});

app.post('/creercompteur', (request,response) => {
  console.log(request.body)

  client.query('INSERT INTO compteurs(numero, nom_abonne, adresse, index, ancien_index, date_releve, quartier) VALUES ($1,$2,$3,$4,$5,$6,$7)',[request.body.numero, request.body.nom_abonne, request.body.adresse, request.body.index, request.body.ancien_index, request.body.date_releve, request.body.quartier ], (error, results) => {
    if (error) {
      throw "erroooooooor"
    }
    else{
    }
  })
});
app.post('/recherchercompteur', (request,response) => {
  console.log(request.body)

  if(request.body.numero != "" && request.body.quartier != ""){
    //numero and quartier search
    query = 'SELECT * FROM compteurs WHERE numero=$1 AND quartier=$2'
  }
  else{
    //numero or quartier search
    query = 'SELECT * FROM compteurs WHERE numero=$1 OR quartier=$2'
  }
  

  client.query(query,[request.body.numero, request.body.quartier], (error, results) => {
    if (error) {
      throw "erroooooooor"
    }
    else{
      console.log(results.rows[0]);
      response.send(results.rows)
    }
  })
});

app.get('/users', (request,response) => {
  //code to perform particular action.
  //To access GET variable use req.query() and req.params() methods.

  console.log("users!!!!");
  client.query('SELECT * FROM users WHERE id!=$1 ORDER BY id ASC',[1], (error, results) => {
    if (error) {
      throw error
    }
    response.send(results.rows);
  })
});

app.delete('/deleteuser/:id', (request,response) => {
  const id = request.params.id;

  console.log(id);
  client.query('DELETE FROM users WHERE id=$1',[id], (error, results) => {
    if (error) {
      throw error
    }
    console.log(results.rows);
    response.send(results.rows);
  })
});

app.post('/creeranomalie', (request,response) => {
  console.log(request.body)

  client.query('INSERT INTO anomalies(numero_compteur, description, date_creation) VALUES ($1,$2,$3)',[request.body.numero_compteur, request.body.description, request.body.date_creation], (error, results) => {
    if (error) {
      throw "erroooooooor"
    }
    else{
      response.send(results.rows);
    }
  })
});

app.get('/anomalies', (request,response) => {

  console.log("anomalies!!!!");
  client.query('SELECT * FROM anomalies ORDER BY id ASC', (error, results) => {
    if (error) {
      throw error
    }
    response.send(results.rows);
  })
});

app.delete('/deleteanomalie/:id', (request,response) => {
  const id = request.params.id;

  console.log(id);
  client.query('DELETE FROM anomalies WHERE id=$1',[id], (error, results) => {
    if (error) {
      throw error
    }
    console.log(results.rows);
    response.send(results.rows);
  })
});

app.delete('/deletecompteur/:id', (request,response) => {
  const id = request.params.id;

  console.log(id);
  client.query('DELETE FROM compteurs WHERE id=$1',[id], (error, results) => {
    if (error) {
      throw error
    }
    console.log(results.rows);
    response.send(results.rows);
  })
});

app.listen(3000, function(err){
    if (err) console.log("Error in server setup")
    console.log("Server listening on port 3000");
})
