from google.appengine.ext import webapp
from google.appengine.ext.webapp.util import run_wsgi_app


HORNET_BLAST_URL = 'http://allen-sauer.com/com.allen_sauer.gwt.game.hornetblast.HornetBlast/HornetBlast.html'


class MainHandler(webapp.RequestHandler):
    def get(self):
        self.redirect(HORNET_BLAST_URL)


app = webapp.WSGIApplication([('/', MainHandler)],
                              debug=True)

def main():
  run_wsgi_app(app)

if __name__ == "__main__":
  main()
