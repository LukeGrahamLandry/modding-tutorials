import flask, random, threading, time, requests, json

app = flask.Flask(__name__)

@app.route("/<path:filename>", methods=["GET"])
def file_reader(filename):
    return flask.send_from_directory("", filename)

app.run(host="0.0.0.0", port="80", threaded=True)

###############################################
## I just use this simple server for testing ##
###############################################
