//valid url validation function
		function validateUrl(query_url){
			var uriRegex = new RegExp('(http|ftp|https)://[a-z0-9\-_]+(\.[a-z0-9\-_]+)+([a-z0-9\-\.,@\?^=%&;:/~\+#]*[a-z0-9\-@\?^=%&;/~\+#])?', 'i');
			if(!query_url && !uriRegex.test(query_url)){
				return false;
			}
			var parseUrl = parseURL(query_url);
			var urlHostname = parseUrl.hostname.trim();
            if (urlHostname == '') {  
                return true;  
            }else {  
                if (urlHostname.toUpperCase() == location.hostname.trim().toUpperCase()) {  
                    return true;  
                }else  
                    return false;  
            } 
		}
		
		function parseURL(str_url){
			var a = document.createElement('a');  
            a.href = str_url;  
            return {  
                source: str_url,  
                protocol: a.protocol.replace(':', ''),  
                hostname: a.hostname,  
                host: a.host,  
                port: a.port,  
                query: a.search  
            };  
		}