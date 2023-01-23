const Discord = require("discord.js")
require('dotenv').config()
const Database = require("@replit/database")
const { default: fetch } = require("node-fetch")
const db = new Database()
const client = new Discord.Client({disableEveryone: true})
const prefix = "!"
const fs = require('fs');

client.on("ready", () => {
  console.log(`Logged in as ${client.user.tag}!`)
  console.log(`Bot is ready! ${client.user.username}`);
  client.user.setActivity('with your feelings (๑╹ω╹๑)', { type: 'PLAYING' });
})

  client.on("guildCreate", guild => {
    let defaultChannel = "";
    guild.channels.cache.forEach((channel) => {
    if(channel.type == "text" && defaultChannel == "") {
    if(channel.permissionsFor(guild.me).has("SEND_MESSAGES")) {
    defaultChannel = channel;
    }
    }
    })
    defaultChannel.send('(≧∇≦)/ OHAYO my name is bottyBotFace!');

    defaultChannel.send('!help');
    });
// _________________________________________________________________________________________________________________________________________________
// ------------------------------------------------------------Message replyt ja komennot-----------------------------------------------------------
client.on("message", message => {
  let msg = message.content.toUpperCase();
  let sender = message.author;
  let cont = message.content.slice(prefix.lenght).split(" ");
  var args = cont.slice(1);
  let viesti = message.channel.name + ' || ' + sender.tag + ' || ' + message.content
  // CHAT LOG <channel> || <username> || <message>
  fs.writeFile('./text-logs/test.txt', viesti + '\n',  {'flag':'a'},  function(err) {
    if (err) {
        return console.error(err);
    }
  });

  if (message.content === message.content) {
    if (message.author.bot) return;
    console.log(viesti)
  }


  if (message.content === "moi") {
    message.reply("Moro, Miten menee?");
  }
  if (message.content === "iha hyvin") {
    message.reply("hyvä et menee hyvin")
  }
  // 69
  if (message.content === "69") {
    message.channel.send(":ok_hand: nice, seks number")
  }
  // test for loop
  if (message.content === "testing") {
    for (i=0; i <=10; i++) {
      message.reply("test " + i);
    }
  }
  // ayy lmao
  if (msg.startsWith("AYY")) {
    message.channel.send('Lmao');
  }
  // good bot
  if (msg.startsWith("GOOD BOT")) {
    message.channel.send('(//∀//) t-t-thanks ( 〃．．)');
  }
  // ping: !ping
  if (message.content === `${prefix}ping`) {
		message.channel.send('Pong.');
  }
  // -----------------------------------------------------------!purge Input: [!purge @user <amount>] & [!purge <amount>]-------------------------------------
  if (msg.startsWith(prefix + 'PURGE')) {
    async function purge() {
      
      //--------------------------------------------WHITELIST------------------------------- 
      if (!message.member.roles.cache.some(r => r.name === "GOD" || r.name === "Supreme Being" || r.name === "Literaly Hitler")) {
        message.channel.send('You need the \`Literaly Hitler\` or \`Supreme Being\` role to use this command');
        return;
      }

      const user = message.mentions.users.first();
    
            let amount = !!parseInt(message.content.split(' ')[1]) ? parseInt(message.content.split(' ')[1]) : parseInt(message.content.split(' ')[2]);
            amount = Math.floor(amount + 1);
            if (!amount) return message.channel.send('ᕙ(⇀‸↼‶)ᕗAnna Purgettavien viestien NUMERO.ᕙ(⇀‸↼‶)ᕗ \n Usage: '+ prefix +'purge <amount>');
            if (!amount && !user) return message.channel.send('ᕙ(⇀‸↼‶)ᕗ anna KÄYTTÄJÄNIMI ja NUMERO.ᕙ(⇀‸↼‶)ᕗ \n Usage: '+ prefix +'purge @user <amount>');
            if (amount > 100)return message.channel.send('ᕙ(⇀‸↼‶)ᕗ  Poistettavien viestien määrä pitää olla alle 100 ᕙ(⇀‸↼‶)ᕗ');
          
            message.channel.messages.fetch({ // hakee viestit kanavalta ja asettaa 100limit että voi poistaa enintään 100 viestiä
             limit: 100,
            }).then((messages) => { //sitten viestit
             if (user) { //jos komennossa on käyttäjä !purge @user <amount>
             const filterBy = user ? user.id : Client.user.id; //user id että voi @user
             messages = messages.filter(m => m.author.id === filterBy).array().slice(0, amount); //asettaa filtterin komennolla määritetyn userin komennolla määritetyn viestimäärän (bulkdeletelle) 
             } 
             else {
                messages = amount; //jos ei ole @user vaan pelkkä !purge <amount>
             }
                console.log(parseInt (amount) -1 + ' messages found, deleting desu~~...'); //consoliin kuinka monta viestiä löydetty ja poistetaan (-1 koska komentoa ei lasketa)
                message.channel.bulkDelete(messages).catch(error => console.log(error.stack)); //buldelete poistaa viestit ja nappaa errorin jos sellainen on
            });
    }
    purge();

  }
  // _______________________________________________________________________________________________________________________________________________
  
  // 8ball: !8ball <kysymys>
  if (msg.startsWith(prefix + '8BALL')) { 
      // save responses to an array
      let responses = ["Yes", "No", "Maybe", "Most likely", "Without a doubt", "Outlook not so good", "Outlook good", "Very doubtful"];

      // pick a random response
      let resp = responses[Math.floor(Math.random() * responses.length)];
  
      // send a message
      message.channel.send("8ball said: " + resp + " desu~~");
  }
  // help: !help
  if (msg.startsWith(prefix + 'HELP')) return message.channel.send('```----------------------------------INFO----------------------------------  \n ' + ' ' +' \n (￣ω￣) !purge <amount> || !purge <@user> <amount> = Voit poistaa enintään 100 2viikkoa vanhaa viestiä kerrallaan \n ' + ' ' +' \n ᕕ( ᐛ )ᕗ !8ball <kysymys> = perus kasipallo vastaa randomisti kysymykseesi esim <is @dicklover gay?> vastaus <no desu> \n ' + ' ' +' \n（‐＾▽＾‐）!coin = kolikon heitto perus heads or tails\n ' + ' ' +' \n ------------------------------------------------------------------------ ```');

  // kolikko: !coin
  let kolikko = Math.floor(Math.random() * 2)+1;
  if (msg.startsWith(prefix + 'COIN')) {
    if (kolikko == 1) {
      message.channel.send('heads :coin:');
    }
    else {
      message.channel.send('tails :coin:');
    }
    return;
  } 
  // Saa botti sanomaan mitä kirjotat: !say <tekstiä>
  if (message.author.bot) return;
  if (msg.startsWith(prefix + 'SAY')) {
    let SayMessage = message.content.slice(4).trim();
    message.channel.send(SayMessage);
    message.delete();
  }
})
// _________________________________________________________________________________________________________________________________________________
// _________________________________________________________________________________________________________________________________________________

client.login(process.env.TOKEN);