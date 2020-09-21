import flask, random, threading, time, requests, json

config = {
    "hidden files": []
}

app = flask.Flask(__name__)

@app.route("/<path:filename>", methods=["GET"])
def file_reader(filename):
    if filename in config["hidden files"]:
        return flask.abort(403)

    return flask.send_from_directory("", filename)


@app.route("/server", methods=["POST"])
def request_endpoint():
    request_data = json.loads(flask.request.data.decode("UTF-8"))
    response = process_request(request_data)
    if response is None:
        raise ValueError("INVALID REQEUST")
    return json.dumps(response)

def process_request(r):
    page = r["PATH"].split("/")[-1].split(".")[0]
    action = r["action"]

    return {"msg": "Hello World"}




app.run(host="0.0.0.0", port="80", threaded=True)