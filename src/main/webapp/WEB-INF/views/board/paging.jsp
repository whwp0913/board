<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%	
	int currentPage = StringUtils.isBlank(request.getParameter("page")) ? 1 : Integer.parseInt(request.getParameter("page"));	
	int totalCount = (int)request.getAttribute("totalCount");
	int pageGroup = 10;
	int totalPage = totalCount / pageGroup;
	
	if(totalPage * pageGroup < totalCount ) {
		totalPage++;
	}
	
	if(currentPage > totalPage) {
		currentPage = totalPage;
	}
	
	int startPage = ((currentPage-1)/pageGroup) * pageGroup + 1;
	int endPage = startPage + pageGroup - 1;
	
	if(endPage > totalPage) {
		endPage = totalPage;
	}
	pageContext.setAttribute("currentPage", currentPage);
	pageContext.setAttribute("totalCount", totalCount);
	pageContext.setAttribute("pageGroup", pageGroup);
	pageContext.setAttribute("totalPage", totalPage);
	pageContext.setAttribute("startPage", startPage);
	pageContext.setAttribute("endPage", endPage);
%>

<nav>
  <ul class="pagination">
  	<c:if test="${currentPage > pageGroup}">
	    <li>
	      <a href="/board/list?page=${currentPage-1}" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
    </c:if>
    <c:forEach begin="${startPage }" end="${endPage }" var="idx">
   		<li><a href="/board/list?page=${idx}">${idx}</a></li>
   	</c:forEach>
   	<c:if test="${startPage + pageGroup < totalPage}">
	    <li>
	      <a href="/board/list?page=${currentPage+1 }" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
    </c:if>
  </ul>
</nav>