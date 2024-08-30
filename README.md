<img src="https://raw.githubusercontent.com/MicaelliMedeiros/micaellimedeiros/master/image/computer-illustration.png" alt="ilustração de um computador" min-width="400px" max-width="400px" width="400px" align="right">

<p align="left"> 
  O sistema Prescript é um sistema de cadastro de Prescrição Médica.
</p>

<p align="left">
  :fire: Firebase: Utilizando o Firebase o sistema efetua login através de e-mail e senha. Também, é possivel cadastrar novos usuários e recuperar a senha. Utilizandoo Firestore, o sistema salva os registros do medicamentos. 
</p>

<p align="left">
  :link: Link do vídeo do youtube explicando o funcionamento do sistema: https://youtu.be/bzMwDZdirmg?feature=shared
</p>
<p align="left">
  :pill: Funcionalidade: Ao realizar o login o usuário e redirecionado para uma tela de lista de medicamentos. Que mostra os medicamentos cadastrados. Também, é possível realizar o CRUD desses medicamentos.
</p>


## Tela de Login

<img src="https://github.com/user-attachments/assets/d08a14b2-cf96-454c-b253-3147878b2673" alt="ilustração de um computador" min-width="200px" max-width="200px" width="200px" align="right">


<p align="left">
  :lock: A tela de login do sistema Prescript utiliza o FireBase Authentication, através de e-mail e senha, para a realização de acesso ao sistema.
  O primeiro passo para que isso aconteça é realizando o cadastro do usuário.
</p>

### -> Cadastro de usuário

Ao clicar no botão **Cadastrar** o usuário é redirecionado para a tela de cadastro de novos usuários, abaixo.

<img src="https://github.com/user-attachments/assets/48421606-34ce-4781-9731-59ba8fafe1fc" alt="ilustração de um computador" min-width="100px" max-width="100px" width="100px" align="left">

<p align="left">
  O usuário deve informar um e-mail válido e uma senha. Caso o usuário deixe de preencher algum campo, o sistema não permitirá o cadastro e informará que os campos precisam ser preenchidos.
  O mesmo ocorre se o usuário não informar um e-mail válido.
</p>

<p align="left">
  Outra verificação realizada pelo sistema ocorre se o usuário informar um e-mail já cadastrado. O sistema não permitirá o cadastro e também informará através de mensagem que o e-mail já existe na base de dados. 
</p>

<p align="left">
  Realizado o cadastro o usuário estará apto à acessar o sistema. 
</p>

### -> Esqueceu a senha

Caso o usuário esqueça a senha informada no cadastro, o mesmo poderá modificá-la clicando no link **Esqueceu a senha?**. Então o usuário será redirecionado para a tela de recuperação de senha, abaixo.

<img src="https://github.com/user-attachments/assets/537878e7-981d-4781-8b3d-7ab2e2892b6d" alt="ilustração de um computador" min-width="100px" max-width="100px" width="100px" align="left">

<p align="left">
  Na tela de recuperação de senha o usuário deverá informar um e-mail valido. Caso seja informado um e-mail inválido o sistema informará o usuário através de mensagem que não é possível realizar a recuperação da senha.
  Também, se o usuário informar um e-mail válido, mas que não esteja cadastrado na base de dados, o sistema informará que a recuperação foi realizada com sucesso, mas nenhum e-mail será enviado.
</p>

<p align="left">
  Já se o usuário informar um e-mail válido e cadastrado no sistema, o FireBase irá enviar em e-mail para o endereço informado com um link. Ao clicar no link, o usuário será redirecionado para uma tela onde poderá informar uma nova senha.
</p>  

<p align="left">
  Informando a nova senha o usuário já estará apto para acessar sistema. 
</p>
